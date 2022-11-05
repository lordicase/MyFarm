package com.example.myfarm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class Backgroundr extends AsyncTask <String, Void,String> {

    AlertDialog dialog;
    Context context;

    public Backgroundr(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Status rejestracji");
    }
    @Override
    protected void onPostExecute(String s) {
        dialog.setMessage(s);

        if(s.contains("udana rejestracja"))
        {
            dialog.show();
            //przenoszenie na główną
            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(), MainActivity.class);
            context.startActivity(intent_name);
        }else{


            if (s.contains("A")) {
                s = "Login nie może posiadać polskich znaków!";
            }
            else if (s.contains("B")) {
                s = "Login musi się składać z conajmniej 3 znaków!";
            }
            else if (s.contains("C")) {
                s = "Proszę podać poprany adres e-mail!";
            }
            else if (s.contains("D")) {
                s = "Hasło musi się składać z przynajmniej 10 znaków!";
            }
            else if (s.contains("E")) {
                s = "Podane hasła nie są takie same!";
            }
            else if (s.contains("F")) {
                s = "Podany email jest już przypisany do innego konta!";
            }
            else if (s.contains("G")) {
                s = "Podana nazwa użytkownika jest zajęta!";
            }

            dialog.setMessage(s);
            dialog.show();

        }
    }
    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String user = voids[0];
        String pass = voids[1];
        String pass2 = voids[2];
        String email = voids[3];

        String connstr = "https://mzsk.pl/MYFARM/mobilna/rejestracja.php";

        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("nazwa","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")
                    +"&&"+URLEncoder.encode("haslo1","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")
                    +"&&"+URLEncoder.encode("haslo2","UTF-8")+"="+URLEncoder.encode(pass2,"UTF-8")
                    +"&&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line ="";
            while ((line = reader.readLine()) != null)
            {
                result += line;
            }
            reader.close();
            ips.close();
            http.disconnect();
            return result;

        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }


        return result;
    }
}
