package com.example.myfarm.ui.zabiegi;

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
import com.example.myfarm.ui.bydlo.Formzb;

import java.util.Calendar;

public class Formzz extends AppCompatActivity {

    public static String data;

    private CalendarView calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_z_z);

        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");


        EditText opis = (EditText) findViewById(R.id.EditText25);
        opis.setText(Glowna.opis_z.get(Integer.valueOf(id)));

        calender = (CalendarView)findViewById(R.id.calendarView);
        String date = Glowna.data_z.get(Integer.valueOf(id));
        String parts[] = date.split("-");

        int day = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[1])-1;
        int year = Integer.parseInt(parts[0]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        long milliTime = calendar.getTimeInMillis();

        //USTAWIANIE DATY W KALENDARZU
        calender.setDate(milliTime, true, true);
        //USTAWIENIE ZMIENNEJ DO WYSŁANIA W RAZIE NIE ZMIENIANIA DATY PRZEZ UŻYTKOWNIKA
        if (month<9){
            Formzz.data =year+"-0"+(month+1)+"-"+day;
            if(day<10){
                Formzz.data =year+"-0"+(month+1)+"-0"+day;
            }
        }else if(day<10){
            Formzz.data =year+"-"+(month+1)+"-0"+day;
        }
        if((month>9) && (day>10)){
            Formzz.data =year+"-"+(month+1)+"-"+day;
        }

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Formzz.data =year+"-"+(month+1)+"-"+dayOfMonth;
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


        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        String id_wpisu = Glowna.id_z.get(Integer.valueOf(id));

        String user = String.valueOf(Background.id);

        Formzz_background sign = new Formzz_background(this);
        sign.execute(data_w, nazwa_d_w, typ_w, opis_w,user,id_wpisu);
    }
}
