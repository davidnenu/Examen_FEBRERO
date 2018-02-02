package com.example.dm2.examen_febrero;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class EJtiempo extends AppCompatActivity {



    private TextView resultado;

    private Tiempo tiempo;
    private TextView localidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eltiempo);
        tiempo = new Tiempo();
        resultado =findViewById(R.id.textViewResumenTiempo);
        localidad=findViewById(R.id.texttiempoen);

    }
    public void volver(View v){
        finish();
    }
    public void vitoria(View v){
        resultado.setText("");
        localidad.setText("VITORIA-GASTEIZ");
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute("http://xml.tutiempo.net/xml/8043.xml");
    }
    public void bilbao(View v){
        resultado.setText("");
        localidad.setText("BILBO-BILBAO");
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute("http://xml.tutiempo.net/xml/8050.xml");
    }
    public void donostia(View v){
        resultado.setText("");
        localidad.setText("DONOSTIA-SAN SEBASTIAN");
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute("http://xml.tutiempo.net/xml/4917.xml");
    }

    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {
        protected Boolean doInBackground(String... params) {
            RssParserSax saxparser = new RssParserSax(params[0]);
            //Log.i("------------>>>", "URL: "+params[0]);
            tiempo = saxparser.parse();
            //Log.i("aa", "Tamaño noticias: "+noticias.size());
            return true;
        }

        protected void onPostExecute(Boolean result) {
            resultado.setText(resultado.getText()+"\n"+tiempo.getHora());
            resultado.setText(resultado.getText()+"\n"+tiempo.getTemp());
            resultado.setText(resultado.getText()+"\n"+tiempo.getEstado());

        }
    }



}
