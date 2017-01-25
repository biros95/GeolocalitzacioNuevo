package com.example.marcosportatil.geolocalitzacio.baseDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class AutobusDBHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Horario.db";
    private String tabla = "AUTOBUSLOGIN";
    private String CreacionTabla = "CREATE TABLE " + tabla + " (MATRICULA TEXT NOT NULL, PASSWORD TEXT NOT NULL)";
    private String InsercionTabla = "INSERT INTO " + tabla + " VALUES ('9999BBB', '1234')";
    private String SelectTabla = "SELECT * FROM " + tabla;


    public AutobusDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreacionTabla);
        db.execSQL(InsercionTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean selectBaseDatos(String login, String password) {


        Cursor c = getReadableDatabase().rawQuery(SelectTabla + " WHERE MATRICULA LIKE ? AND PASSWORD LIKE ?", new String[]{login, password});
        Log.i("info", "" + c.getCount() + login + password);
        if (c.getCount() > 0) {
            c.close();
            return true;

        } else {


            c.close();
            return false;
        }
    }

}
