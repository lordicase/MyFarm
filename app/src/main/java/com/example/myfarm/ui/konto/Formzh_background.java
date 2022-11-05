package com.example.myfarm.ui.konto;

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


public class Formzh_background extends AsyncTask<String, Void, String> {

    Context context;
    public Formzh_background(Context ctx) {
        this.context=ctx;
    }



    @Override
    protected void onPostExecute(String s) {
        if (s.contains("A")) {
            Glowna.page=7;
            Toasty.success(context.getApplicationContext(), "Hasło został zmieniony pomyślnie!", Toast.LENGTH_LONG).show();
            Intent przeniesienie = new Intent();
            przeniesienie.setClass(context.getApplicationContext(), Glowna.class);
            context.startActivity(przeniesienie);

        }else if (s.contains("B")) {
            Toasty.error(context.getApplicationContext(), "Błąd podczas zmiany hasła, proszę spróbować później...", Toast.LENGTH_LONG).show();
        }else if (s.contains("C")) {
            Toasty.error(context.getApplicationContext(), "Hasło musi posiadać od 10 do 20 znaków!", Toast.LENGTH_LONG).show();
        }else if (s.contains("D")) {
            Toasty.error(context.getApplicationContext(), "Podane hasła nie są identyczne!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String haslo1 = voids[0];
        String haslo2 = voids[1];
        String id = voids[2];
        String regURL = "https://mzsk.pl/MYFARM/mobilna/zmien_h.php";

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
                    URLEncoder.encode("haslo1", "UTF-8")+"="+URLEncoder.encode(haslo1, "UTF-8")+"&"+
                    URLEncoder.encode("haslo2", "UTF-8")+"="+URLEncoder.encode(haslo2, "UTF-8")+"&"+
                    URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8");
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
