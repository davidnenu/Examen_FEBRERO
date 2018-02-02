package com.example.dm2.examen_febrero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button tiempo;
    private Button datos;
    private Button multimedia;
    private Button salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void tiempo(View v){
        Intent intent = new Intent(MainActivity.this,EJtiempo.class);
        startActivity(intent);
    }
    public void elemento(View v){
        Intent intent = new Intent(MainActivity.this,EJwebservices.class);
        startActivity(intent);
    }
    public void multimedia(View v){
        Intent intent = new Intent(MainActivity.this,EJmultimedia.class);
        startActivity(intent);
    }
    public void volver(View v){
        finish();
    }
}
