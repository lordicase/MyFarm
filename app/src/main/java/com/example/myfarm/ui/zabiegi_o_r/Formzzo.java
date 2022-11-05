package com.example.myfarm.ui.zabiegi_o_r;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfarm.Background;
import com.example.myfarm.Glowna;
import com.example.myfarm.R;
import com.example.myfarm.ui.zabiegi.Formzz;

import java.util.Calendar;

public class Formzzo extends AppCompatActivity {

    public static String data;

    private CalendarView calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_z_z_o);

        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");


        EditText uprawa = (EditText) findViewById(R.id.EditText12);
        EditText obszar = (EditText) findViewById(R.id.EditText18);
        EditText nazwa_s = (EditText) findViewById(R.id.EditText26);
        EditText dawka = (EditText) findViewById(R.id.EditText27);
        EditText uwagi = (EditText) findViewById(R.id.EditText28);

        uprawa.setText(Glowna.uprawa_z_o_r.get(Integer.valueOf(id)));
        obszar.setText(Glowna.pow_z_o_r.get(Integer.valueOf(id)));
        nazwa_s.setText(Glowna.nazwa_s_z_o_r.get(Integer.valueOf(id)));
        dawka.setText(Glowna.dawka_z_o_r.get(Integer.valueOf(id)));
        uwagi.setText(Glowna.uwagi_z_o_r.get(Integer.valueOf(id)));


        calender = (CalendarView)findViewById(R.id.calendarView);
        String date = Glowna.data_z_o_r.get(Integer.valueOf(id));
        String parts[] = date.split("-");

        int day = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[1])-1;
        int year = Integer.parseInt(parts[0]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        long milliTime = calendar.getTimeInMillis();
        calender.setDate(milliTime, true, true);


        //USTAWIENIE ZMIENNEJ DO WYSŁANIA W RAZIE NIE ZMIENIANIA DATY PRZEZ UŻYTKOWNIKA
        if (month<9){
            Formzzo.data =year+"-0"+(month+1)+"-"+day;
            if(day<10){
                Formzzo.data =year+"-0"+(month+1)+"-0"+day;
            }
        }else if(day<10){
            Formzzo.data =year+"-"+(month+1)+"-0"+day;
        }
        if((month>9) && (day>10)){
            Formzzo.data =year+"-"+(month+1)+"-"+day;
        }
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Formzzo.data =year+"-"+(month+1)+"-"+dayOfMonth;
            }
        });


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Glowna.nazwa_w);
// Specify the layout to use when the list of choices appears
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(arrayAdapter);
    }

    public void FormA(View view) {

        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        String nazwa_d = spinner.getSelectedItem().toString();
        EditText uprawa = (EditText) findViewById(R.id.EditText12);
        EditText obszar = (EditText) findViewById(R.id.EditText18);
        EditText nazwa_s = (EditText) findViewById(R.id.EditText26);
        EditText dawka = (EditText) findViewById(R.id.EditText27);
        EditText uwagi = (EditText) findViewById(R.id.EditText28);

        String nazwa_d_w = nazwa_d;
        String uprawa_w = uprawa.getText().toString();
        String obszar_w = obszar.getText().toString();
        String data_w = data;
        String nazwa_s_w = nazwa_s.getText().toString();
        String dawka_w = dawka.getText().toString();
        String uwagi_w = uwagi.getText().toString();

        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        String id_wpisu = Glowna.id_z_o_r.get(Integer.valueOf(id));

        String user = String.valueOf(Background.id);

        Formzzo_background sign = new Formzzo_background(this);
        sign.execute(nazwa_d_w, uprawa_w, obszar_w, data_w,nazwa_s_w,dawka_w,uwagi_w,user,id_wpisu);
}
}
