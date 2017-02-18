package com.example.marcosportatil.geolocalitzacio;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void buttonListener(View view) {

        matriculaEditText = (EditText) findViewById(R.id.editTextMatricula);
        passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        baseDatos = new AutobusDBHelper(this);

        boolean comprova = true;
        if (!(matriculaEditText.getText().toString().equals(null))) {
            if (baseDatos.selectBaseDatos(matriculaEditText.getText().toString(),
                    passwordEditText.getText().toString())) {
                Intent i = new Intent(getApplicationContext(), Localitzacio.class);
                i.putExtra("matricula", matriculaEditText.getText().toString());
                Toast.makeText(this,"Correcto", Toast.LENGTH_SHORT).show();
                startService(i);
                if (isServiceRunning(Localitzacio.class)){
                    Toast.makeText(this,"Correcto", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Usuari o contrasenya incorrecte", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Introdueix matricula", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isServiceRunning(Class<?> serviceClass){
        ActivityManager activityManager = (ActivityManager) getSystemService(this.ACTIVITY_SERVICE);

        // Loop through the running services
        for(ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                // If the service is running then return true
                return true;
            }
        }
        return false;
    }
}
