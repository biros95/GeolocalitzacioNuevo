package com.example.marcosportatil.geolocalitzacio;

import android.*;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcosportatil.geolocalitzacio.Localizacion.Localitzacio;
import com.example.marcosportatil.geolocalitzacio.baseDatos.AutobusDBHelper;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReciver;
    private AutobusDBHelper baseDatos;

    private EditText matriculaEditText;
    private EditText passwordEditText;
    private Button btnInicio;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInicio = (Button) findViewById(R.id.buttonLogin);
        matriculaEditText = (EditText) findViewById(R.id.editTextMatricula);
        passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        if(!runtime_permissions()) {
            enable_buttons();

        }


    }

    public boolean comprobarConexion() {


        baseDatos = new AutobusDBHelper(this);


        if (!(matriculaEditText.getText().toString().equals(null))) {
            if (baseDatos.selectBaseDatos(matriculaEditText.getText().toString(),
                    passwordEditText.getText().toString())) {
                Toast.makeText(this,"Correcto", Toast.LENGTH_SHORT).show();
                return true;

            } else {
                Toast.makeText(this, "Usuari o contrasenya incorrecte", Toast.LENGTH_SHORT).show();
                return false;
            }

        } else {
            Toast.makeText(this, "Introdueix matricula", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean runtime_permissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},100);

            return true;
        }
        return false;
    }

    private void enable_buttons() {


        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comprobarConexion()) {
                    Intent i = new Intent(getApplicationContext(),Localitzacio.class);
                    i.putExtra("matricula", matriculaEditText.getText().toString());
                    Log.i("SI ", "se abre el servicio");
                    startService(i);
                }
                else {
                    Log.i("NO ", "se abre el servicio");
                }
            }
        });



    }
}
