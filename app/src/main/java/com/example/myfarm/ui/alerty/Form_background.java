package com.example.myfarm.ui.alerty;

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


public class Form_background extends AsyncTask<String, Void, String> {

    Context context;
    String up="Uprawa";
    String sz="Szkodnik";
    public Form_background(Context ctx) {
        this.context=ctx;
    }



    @Override
    protected void onPostExecute(String s) {
        if(sz.equals("Szkodnik")||up.equals("Uprawa"))
        {
            Toast.makeText(context.getApplicationContext(), "Wybierz uprawę i szkodnika", Toast.LENGTH_LONG).show();

        }

        else if (s.contains("A")) {
            Glowna.page=4;
            Toasty.success(context.getApplicationContext(), "Alert dodany pomyślnie!", Toast.LENGTH_LONG).show();
            Intent przeniesienie = new Intent();
            przeniesienie.setClass(context.getApplicationContext(), Glowna.class);
            context.startActivity(przeniesienie);

        }
        else if (s.contains("B")) {
            Glowna.page=4;
            Toasty.error(context.getApplicationContext(), "Błąd podczas dodawania alertu, proszę spróbować później...", Toast.LENGTH_LONG).show();
            Intent przeniesienie = new Intent();
            przeniesienie.setClass(context.getApplicationContext(), Glowna.class);
            context.startActivity(przeniesienie);
        }else{
            Glowna.page=4;
            Toasty.error(context.getApplicationContext(), "Wyczerpano limit znaczników. Poczekaj na odnowienie limitu", Toast.LENGTH_LONG).show();        }
            Intent przeniesienie = new Intent();
            przeniesienie.setClass(context.getApplicationContext(), Glowna.class);
            context.startActivity(przeniesienie);
    }

    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String uprawa = voids[0];
        String szkodnik = voids[1];
        up=voids[0];
        sz=voids[1];
        String latitude = voids[2];
        String longitude = voids[3];
        String user = voids[4];
        String regURL = "https://mzsk.pl/MYFARM/mobilna/dodaj_s.php";


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
                    URLEncoder.encode("szkodnik", "UTF-8")+"="+URLEncoder.encode(szkodnik, "UTF-8")+"&"+
                    URLEncoder.encode("uprawa", "UTF-8")+"="+URLEncoder.encode(uprawa, "UTF-8")+"&"+
                    URLEncoder.encode("lat", "UTF-8")+"="+URLEncoder.encode(latitude, "UTF-8")+"&"+
                    URLEncoder.encode("lon", "UTF-8")+"="+URLEncoder.encode(longitude, "UTF-8")+"&"+
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
