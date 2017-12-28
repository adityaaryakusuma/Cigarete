package com.example.shonen.cigarete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by VergieClariias on 28/12/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    static final private String Db_NAME = "Cigarete";
    static final private String ID = "_id";
    static final private int Db_VER = 6;

    ////deklarasi nama tabel
    static final private String TB_INPUT = "Input";//tabel input
    static final private String CREATE_TB_INPUT = "create table " + TB_INPUT + "(_id integer primary key autoincrement,nama_rokok text,harga_rokok integer);";//tabel rokok
    static final private String TB_TRANSAKSI = "Transaksi";//tabel pesanan
    static final private String CREATE_TB_TRANSAKSI = "create table " + TB_TRANSAKSI + "(_id integer primary key autoincrement,nama text, harga integer, jumlah integer, total integer);";//tabel transaksi
    Context mycontext;

    SQLiteDatabase myDb;
    public DbHelper(Context context) {
        super(context, Db_NAME, null, Db_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_INPUT);
        db.execSQL(CREATE_TB_TRANSAKSI);
        Log.i("Database", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TB_INPUT);
        db.execSQL("drop table if exists " + TB_TRANSAKSI);
        onCreate(db);
    }

    public void insertDatabarang(String p1, int p2) {
        myDb = getWritableDatabase();
        myDb.execSQL("insert into " + TB_INPUT + " (nama_rokok,harga_rokok) values('" + p1 + "','" + p2 + "');");
    }

    public Cursor readAllbarang() {
        myDb = getWritableDatabase();
        String[] columns = new String[]{"_id", "nama_rokok", "harga_rokok"};
        Cursor c = myDb.query(TB_INPUT, columns, null, null, null, null, ID + " asc");
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectedbarang(long id) {
        myDb=getWritableDatabase();
        String[] columns = new String[]{"_id","nama_rokok", "harga_rokok"};
        Cursor c = myDb.query(TB_INPUT, columns, ID + "=" + id, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void deletebarang(long id) {
        myDb=getWritableDatabase();
        myDb.delete(TB_INPUT, ID + "=" + id, null);
        myDb.close();
    }

    public void updatebarang(long id, String p1,int p2) {
        myDb=getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama_rokok", p1);
        values.put("harga_rokok",p2);
        myDb.update(TB_INPUT, values, ID + "=" + id, null);
        close();
    }

    public void insertDatatransaksi(String p1, int p2,int p3,int p4) {
        myDb = getWritableDatabase();
        myDb.execSQL("insert into " + TB_TRANSAKSI + " (nama ,harga,jumlah, total) values('" + p1 + "','" + p2 + "','" + p3 + "','" + p4 + "');");
    }

    public Cursor readAlltransaksi() {
        myDb = getWritableDatabase();
        String[] columns = new String[]{"_id", "nama","harga","jumlah","total"};
        Cursor c = myDb.query(TB_TRANSAKSI, columns, null, null, null, null, ID + " asc");
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
}
