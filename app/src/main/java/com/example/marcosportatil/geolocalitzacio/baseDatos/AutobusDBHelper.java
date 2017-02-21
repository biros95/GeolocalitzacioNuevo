package com.example.marcosportatil.geolocalitzacio.baseDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.marcosportatil.geolocalitzacio.MainActivity;


public class AutobusDBHelper extends SQLiteOpenHelper {

    //Variables
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Horario.db";
    private String tabla = "AUTOBUSLOGIN";
    private String CreacionTabla = "CREATE TABLE " + tabla + " (MATRICULA TEXT NOT NULL, PASSWORD TEXT NOT NULL)";
    private String InsercionTabla = "INSERT INTO " + tabla + " VALUES ('9999BBB', '1234')";
    private String SelectTabla = "SELECT * FROM "   + tabla;

    //Constructor
    public AutobusDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /*
     * En el onCreate ejecuta las sentencias (Crea la tabla e inserta en ella).
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreacionTabla);
        db.execSQL(InsercionTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    /*
     * Haz un select en la base de datos por matricula y contraseña.
     */
    public boolean selectBaseDatos(String login, String password) {

        //Retorna si la busqueda ha obtenida algun resultado(true) o ninguno(false).
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
