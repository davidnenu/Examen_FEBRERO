package com.example.dm2.examen_febrero;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EJmultimedia extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    ArrayList<String> a = new ArrayList<>();
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejmultimedia);

        spinner = (Spinner) findViewById(R.id.spinner);

        a = leerREC();


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, a);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }
    public void volver(View v){
            finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView,
                               View view, int position, long l) {
        TextView tvMensaje = (TextView) view;
        Toast.makeText(this, "Has seleccionado " + tvMensaje.getText()
                + " en la posición " + (position + 1), Toast.LENGTH_SHORT).show();
        if(tvMensaje.getText().equals("burro"))
        mp = MediaPlayer.create(this, R.raw.burro);
        else if(tvMensaje.getText().equals("caballo"))
            mp = MediaPlayer.create(this, R.raw.caballo);
        else if(tvMensaje.getText().equals("cabra"))
            mp = MediaPlayer.create(this, R.raw.cabra);
        else if(tvMensaje.getText().equals("gallina"))
            mp = MediaPlayer.create(this, R.raw.gallina);
        else if(tvMensaje.getText().equals("gallo"))
            mp = MediaPlayer.create(this, R.raw.gallo);
        else if(tvMensaje.getText().equals("gato"))
            mp = MediaPlayer.create(this, R.raw.gato);
        else if(tvMensaje.getText().equals("oveja"))
            mp = MediaPlayer.create(this, R.raw.ovejas);
        else if(tvMensaje.getText().equals("vaca"))
            mp = MediaPlayer.create(this, R.raw.vaca);

        mp.start();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


    public ArrayList<String> leerREC() {
        try {
            ArrayList<String> aux = new ArrayList<String>();
            InputStream fraw = getResources().openRawResource(R.raw.recurso);
            BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
            String linea = brin.readLine();
            //contenidoMostrar.setText("");
            while (linea != null) {
                aux.add(linea);
                //contenidoMostrar.setText(contenidoMostrar.getText().toString() +linea+"\n");
                linea = brin.readLine();
            }
            fraw.close();
            return aux;
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");
        }
        return null;
    }

    }

