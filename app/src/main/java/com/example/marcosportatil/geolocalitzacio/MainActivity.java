package com.example.marcosportatil.geolocalitzacio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SQLiteDatabase db;
    private String login;
    private String password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText matriculaEditText = (EditText) findViewById(R.id.editTextMatricula);
        EditText passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        login = matriculaEditText.getText().toString();
        password = passwordEditText.getText().toString();



        Button loginButton = (Button) findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }


}
