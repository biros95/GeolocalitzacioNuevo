package com.example.marcosportatil.geolocalitzacio.baseDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AutobusDBHelper extends SQLiteOpenHelper{
    private String tabla = "AUTOBUSLOGIN";
    private String CreacionTabla = "CREATE TABLE "+tabla+" (MATRICULA TEXT NOT NULL, PASSWORD TEXT NOT NULL";
    private String InsercionTabla = "INSERT INTO "+tabla+" VALUES ('9999BBB', '1234');";
    private String SelectTabla = "SELECT * FROM "+tabla+";";


    public AutobusDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreacionTabla);
        db.execSQL(InsercionTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public Boolean SelectBaseDatos(SQLiteDatabase db, String login, String Password) {


        Cursor c = db.rawQuery(SelectTabla + " WHERE MATRICULA LIKE "+login+" AND PASSWORD LIKE "+Password, null);

        if (c.getCount() <= 0) {
            c.close();
            return false;

        } else {
            c.close();
            return true;
        }
    }

}
