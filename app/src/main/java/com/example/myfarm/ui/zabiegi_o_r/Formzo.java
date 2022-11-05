package com.example.myfarm.ui.zabiegi_o_r;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfarm.Background;
import com.example.myfarm.Glowna;
import com.example.myfarm.R;
import com.example.myfarm.ui.alerty.Alerty;
import com.example.myfarm.ui.zabiegi.Formz;
import com.example.myfarm.ui.zabiegi.Formz_background;

import java.text.SimpleDateFormat;

public class Formzo extends AppCompatActivity {

    public static String data;

    private CalendarView calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_z_o);

        calender = (CalendarView)findViewById(R.id.calendarView);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        data = format.format(calender.getDate());
        calender = (CalendarView)findViewById(R.id.calendarView);
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Formzo.data =year+"-"+(month+1)+"-"+dayOfMonth;
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

        String user = String.valueOf(Background.id);
        Formzo_background sign = new Formzo_background(this);
        sign.execute(nazwa_d_w, uprawa_w, obszar_w, data_w,nazwa_s_w,dawka_w,uwagi_w,user);
}
}
