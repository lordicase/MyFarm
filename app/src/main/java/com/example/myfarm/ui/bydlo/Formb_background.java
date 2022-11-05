package com.example.myfarm.ui.bydlo;

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


public class Formb_background extends AsyncTask<String, Void, String> {

    Context context;

    public Formb_background(Context ctx) {
        this.context=ctx;
    }



    @Override
    protected void onPostExecute(String s) {

         if (s.contains("A")) {
            Glowna.page=6;
             Toasty.success(context.getApplicationContext(), "Wpis dodany pomyślnie!", Toast.LENGTH_LONG).show();
            Intent przeniesienie = new Intent();
            przeniesienie.setClass(context.getApplicationContext(), Glowna.class);
            context.startActivity(przeniesienie);

        }
        else if (s.contains("B")) {
             Toasty.error(context.getApplicationContext(), "Błąd podczas dodawania wpisu, proszę spróbować później...", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String nr_zwierzecia=voids[0];
        String data_urodzenia=voids[1];
        String plec=voids[2];
        String kod_rasy=voids[3];
        String data_oznakowania=voids[4];
        String nr_matki=voids[5];
        String nr_ojca=voids[6];
        String data_przybycia=voids[7];
        String kod_zdarzenia_p=voids[8];
        String dane_przybycia=voids[9];
        String data_ubycia=voids[10];
        String kod_zdarzenia_u=voids[11];
        String dane_ubycia=voids[12];
        String dane_przewoznika=voids[13];
        String user=voids[14];
        String regURL = "https://mzsk.pl/MYFARM/mobilna/dodaj_b.php";


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
                    URLEncoder.encode("nr_zwierzecia", "UTF-8")+"="+URLEncoder.encode(nr_zwierzecia, "UTF-8")+"&"+
                    URLEncoder.encode("data_urodzenia", "UTF-8")+"="+URLEncoder.encode(data_urodzenia, "UTF-8")+"&"+
                    URLEncoder.encode("plec", "UTF-8")+"="+URLEncoder.encode(plec, "UTF-8")+"&"+
                    URLEncoder.encode("kod_rasy", "UTF-8")+"="+URLEncoder.encode(kod_rasy, "UTF-8")+"&"+
                    URLEncoder.encode("data_oznakowania", "UTF-8")+"="+URLEncoder.encode(data_oznakowania, "UTF-8")+"&"+
                    URLEncoder.encode("nr_matki", "UTF-8")+"="+URLEncoder.encode(nr_matki, "UTF-8")+"&"+
                    URLEncoder.encode("nr_ojca", "UTF-8")+"="+URLEncoder.encode(nr_ojca, "UTF-8")+"&"+
                    URLEncoder.encode("data_przybycia", "UTF-8")+"="+URLEncoder.encode(data_przybycia, "UTF-8")+"&"+
                    URLEncoder.encode("kod_zdarzenia_p", "UTF-8")+"="+URLEncoder.encode(kod_zdarzenia_p, "UTF-8")+"&"+
                    URLEncoder.encode("dane_przybycia", "UTF-8")+"="+URLEncoder.encode(dane_przybycia, "UTF-8")+"&"+
                    URLEncoder.encode("data_ubycia", "UTF-8")+"="+URLEncoder.encode(data_ubycia, "UTF-8")+"&"+
                    URLEncoder.encode("kod_zdarzenia_u", "UTF-8")+"="+URLEncoder.encode(kod_zdarzenia_u, "UTF-8")+"&"+
                    URLEncoder.encode("dane_ubycia", "UTF-8")+"="+URLEncoder.encode(dane_ubycia, "UTF-8")+"&"+
                    URLEncoder.encode("dane_przewoznika", "UTF-8")+"="+URLEncoder.encode(dane_przewoznika, "UTF-8")+"&"+
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
