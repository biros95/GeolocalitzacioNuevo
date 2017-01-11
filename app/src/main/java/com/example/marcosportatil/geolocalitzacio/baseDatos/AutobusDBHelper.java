package com.example.marcosportatil.geolocalitzacio.baseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AutobusDBHelper extends SQLiteOpenHelper{
    private String tabla = "AUTOBUSLOGIN";
    private String CreacionTabla = "CREATE TABLE "+tabla+" (MATRICULA TEXT NOT NULL, PASSWORD TEXT NOT NULL";
    private String InsercionTabla = "INSERT INTO "+tabla+" VALUES ('9999 BBB', '1234');";



    public AutobusDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreacionTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

}
