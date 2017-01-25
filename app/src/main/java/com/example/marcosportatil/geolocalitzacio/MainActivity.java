package com.example.marcosportatil.geolocalitzacio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcosportatil.geolocalitzacio.baseDatos.AutobusDBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private AutobusDBHelper baseDatos;
    private EditText matriculaEditText;
    private EditText passwordEditText;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matriculaEditText = (EditText) findViewById(R.id.editTextMatricula);
        passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        baseDatos = new AutobusDBHelper(this);


        Button loginButton = (Button) findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        if (baseDatos.selectBaseDatos(matriculaEditText.getText().toString(),
                passwordEditText.getText().toString())) {
            Toast toast = Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}
