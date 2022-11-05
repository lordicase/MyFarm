package com.example.myfarm.ui.dzialki;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.myfarm.Glowna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import es.dmoral.toasty.Toasty;


public class Formzd_background extends AsyncTask<String, Void, String> {

    Context context;
    public Formzd_background(Context ctx) {
        this.context=ctx;
    }



    @Override
    protected void onPostExecute(String s) {
        if (s.contains("A")) {
            Glowna.page=1;
            Toasty.success(context.getApplicationContext(), "Działka została zmieniona pomyślnie!", Toast.LENGTH_LONG).show();
            Intent przeniesienie = new Intent();
            przeniesienie.setClass(context.getApplicationContext(), Glowna.class);
            context.startActivity(przeniesienie);

        }
        else if (s.contains("B")) {

            Toasty.error(context.getApplicationContext(), "Błąd podczas zmiany działki, proszę spróbować później...", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String numer_e_d = voids[0];
        String nazwa_w = voids[1];
        String pow = voids[2];
        String uprawa = voids[3];
        String lokalizacja = voids[4];
        String user = voids[5];
        String id = voids[6];

        String regURL = "https://mzsk.pl/MYFARM/mobilna/zmien_d.php";

        try {
            URL url= new URL(regURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            String insert_data =
                    URLEncoder.encode("nr_e_d", "UTF-8")+"="+URLEncoder.encode(numer_e_d, "UTF-8")+"&"+
                    URLEncoder.encode("nazwa_w", "UTF-8")+"="+URLEncoder.encode(nazwa_w, "UTF-8")+"&"+
                    URLEncoder.encode("pow", "UTF-8")+"="+URLEncoder.encode(pow, "UTF-8")+"&"+
                    URLEncoder.encode("uprawa", "UTF-8")+"="+URLEncoder.encode(uprawa, "UTF-8")+"&"+
                    URLEncoder.encode("adres", "UTF-8")+"="+URLEncoder.encode(lokalizacja, "UTF-8")+"&"+
                    URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8")+"&"+
                    URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(user, "UTF-8");
            bufferedWriter.write(insert_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line="";
            StringBuilder stringBuilder = new StringBuilder();
            while ((line=bufferedReader.readLine())!=null) {
                stringBuilder.append(line).append("\n");

            }
            result=stringBuilder.toString();
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


}
