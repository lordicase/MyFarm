package com.example.myfarm.ui.pogoda;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.androdocs.httprequest.HttpRequest;
import com.example.myfarm.Background;
import com.example.myfarm.Glowna;
import com.example.myfarm.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Pogoda extends Fragment {


    String API = "4a3f356baf0693f8e64272d327e900fe";

    TextView addressTxt, updated_atTxt, statusTxt, tempTxt, temp_minTxt, temp_maxTxt, sunriseTxt,
            sunsetTxt, windTxt, pressureTxt, humidityTxt;

    private PogodaViewModel pogodaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pogodaViewModel =
                ViewModelProviders.of(this).get(PogodaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pogoda, container, false);
//        final TextView textView = root.findViewById(R.id.address);



        // super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_pogoda);

        addressTxt = root.findViewById(R.id.address);
        updated_atTxt = root.findViewById(R.id.updated_at);
        statusTxt = root.findViewById(R.id.status);
        tempTxt = root.findViewById(R.id.temp);
        temp_minTxt = root.findViewById(R.id.temp_min);
        temp_maxTxt = root.findViewById(R.id.temp_max);
        sunriseTxt = root.findViewById(R.id.sunrise);
        sunsetTxt = root.findViewById(R.id.sunset);
        windTxt = root.findViewById(R.id.wind);
        pressureTxt = root.findViewById(R.id.pressure);
        humidityTxt = root.findViewById(R.id.humidity);

        new Pogoda.weatherTask().execute();


        return root;

    }

    class weatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        protected String doInBackground(String... args) {
            double LAT = Glowna.LAT;
            double LON = Glowna.LON;
            String response = HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?lat="+ LAT +"&lon="+ LON +"&units=metric&appid=" + API);
            return response;



        }

        @Override
        protected void onPostExecute(String result) {


            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject main = jsonObj.getJSONObject("main");
                JSONObject sys = jsonObj.getJSONObject("sys");
                JSONObject wind = jsonObj.getJSONObject("wind");
                JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);

                Long updatedAt = jsonObj.getLong("dt");
                String updatedAtText = "Updated at: " + new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(new Date(updatedAt * 1000));
                String temp = main.getString("temp") + "°C";
                String tempMin = "Min Temp: " + main.getString("temp_min") + "°C";
                String tempMax = "Max Temp: " + main.getString("temp_max") + "°C";
                String pressure = main.getString("pressure");
                String humidity = main.getString("humidity");

                Long sunrise = sys.getLong("sunrise");
                Long sunset = sys.getLong("sunset");
                String windSpeed = wind.getString("speed");
                String weatherDescription = weather.getString("description");

                String address = jsonObj.getString("name") + ", " + sys.getString("country");


                /* Populating extracted data into our views */
                addressTxt.setText(address);
                updated_atTxt.setText(updatedAtText);
                statusTxt.setText(weatherDescription.toUpperCase());
                tempTxt.setText(temp);
                temp_minTxt.setText(tempMin);
                temp_maxTxt.setText(tempMax);
                sunriseTxt.setText(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(sunrise * 1000)));
                sunsetTxt.setText(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(sunset * 1000)));
                windTxt.setText(windSpeed);
                pressureTxt.setText(pressure);
                humidityTxt.setText(humidity);



            } catch (JSONException e) {

            }

        }


    }


}



