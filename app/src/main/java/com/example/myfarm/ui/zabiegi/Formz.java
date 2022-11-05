package com.example.myfarm.ui.zabiegi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.CalendarView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfarm.Background;
import com.example.myfarm.Glowna;
import com.example.myfarm.R;
import com.example.myfarm.ui.alerty.Alerty;

import java.text.SimpleDateFormat;

public class Formz extends AppCompatActivity {

    public static String data;

    private CalendarView calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_z);

        calender = (CalendarView)findViewById(R.id.calendarView);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        data = format.format(calender.getDate());


        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Formz.data =year+"-"+(month+1)+"-"+dayOfMonth;
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Glowna.nazwa_w);
// Specify the layout to use when the list of choices appears
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(arrayAdapter);



        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.zabiegi, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

    }




    public void FormA(View view) {



        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);


        String nazwa_d = spinner.getSelectedItem().toString();
        String typ = spinner2.getSelectedItem().toString();
        EditText opis = (EditText) findViewById(R.id.EditText25);

        String data_w = data;
        String nazwa_d_w = nazwa_d;
        String typ_w = typ;
        String opis_w = opis.getText().toString();
        Log.i("rok",data_w);

        String user = String.valueOf(Background.id);
        Formz_background sign = new Formz_background(this);
        sign.execute(data_w, nazwa_d_w, typ_w, opis_w,user);
    }
}
