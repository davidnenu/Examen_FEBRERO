package com.example.dm2.examen_febrero;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class EJwebservices extends AppCompatActivity {

    private EditText tvalor;
    private TextView tresultado;
    private String resultado;
    private String numero;
    private String simbolo,natomico,patomico,ebullicion,densidad;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejwebservices);
        tvalor=findViewById(R.id.editTextElemento);

        tresultado=findViewById(R.id.textResultadoElemento);




    }
    public void calcular(View v){
        String valor = tvalor.getText().toString();
        AsyncPost task = new AsyncPost();
        task.execute(valor);
    }
    public void volver(View v){
        finish();
    }

    private class AsyncPost extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                HttpURLConnection conn;
                URL url = new URL
                        ("http://www.webservicex.net/periodictable.asmx/GetAtomicNumber");
                //Codificamos SOLO los valores de los parametros

                //LengthValue=string&fromLengthUnit=string&toLengthUnit=string

                //String param = "LengthValue=" + URLEncoder.encode(params[0], "UTF-8");
                //String param2="fromLengthUnit=" + URLEncoder.encode(params[1],"UTF-8");
                //String param3="toLengthUnit=" + URLEncoder.encode(params[2],"UTF-8");

                String param ="ElementName="+ URLEncoder.encode(params[0],"UTF-8");



                conn = (HttpURLConnection) url.openConnection();
                //establecer la salida a true, indicando que está
                //generando (cargando)datos POST
                conn.setDoOutput(true);
                //una vez establecida la salida a true, no es necesario establecer el
                //metodo de solicitud para publicar, pero lo hacemos de todos modos
                conn.setRequestMethod("POST");
                //La documentación de Android sugiere se establezca la longitud de los
                //datos que está enviando al servidor, PERO
                // NO especificar esta longitud en el encabezado usando
                //conn.setRequestProperty ("Content-Length", length);
                // usa esto en su lugar.
                conn.setFixedLengthStreamingMode(param.getBytes().length);
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                //enviar el POST
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(param);
                out.close();
                //Construye la cadena para almacenar el texto de respuesta
                //desde el servidor
                String result = "";
                resultado = "";
                numero = "";
                //Comienza a escuchar el stream (flujo)
                Scanner inStream = new Scanner(conn.getInputStream());
                //Procesa el stream (flujo) y lo almacena en StringBuilder

                while (inStream.hasNextLine()) {
                    result = inStream.nextLine();
                    resultado += result;
                }
                String[] partes=resultado.split(" ");
                for(int i=0;i<partes.length;i++){
                    if(partes[i].contains("Symbol"))
                        simbolo="Simbolo quimico: "+partes[i].substring(partes[i].indexOf("&gt;")+4,partes[i].indexOf("/")-4);
                    else if(partes[i].contains("AtomicNumber"))
                        natomico="Nº atomico: "+partes[i].substring(partes[i].indexOf("&gt;")+4,partes[i].indexOf("/")-4);
                    else if(partes[i].contains("AtomicWeight"))
                        patomico="Peso atomico: "+partes[i].substring(partes[i].indexOf("&gt;")+4,partes[i].indexOf("/")-4);
                    else if(partes[i].contains("MeltingPoint"))
                        ebullicion="Punto ebullicion: "+partes[i].substring(partes[i].indexOf("&gt;")+4,partes[i].indexOf("/")-4);
                    else if(partes[i].contains("Density"))
                        densidad="Densidad: "+partes[i].substring(partes[i].indexOf("&gt;")+4,partes[i].indexOf("/")-4);
                }

            } catch (MalformedURLException e) {
                Log.e("A", "excepcion MalformedURLException: " + e.getMessage());
            } catch (ProtocolException e) {
                Log.e("A", "excepcion ProtocolException: " + e.getMessage());
            } catch (IOException e) {
                Log.e("A", "excepcion IOException: " + e.getMessage());
            } catch (Exception e) {
                Log.e("A", "excepcion Exception: " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            tresultado.setText(simbolo+"\n"+natomico+"\n"+patomico+"\n"+ebullicion+"\n"+densidad);

        }
    }
}
