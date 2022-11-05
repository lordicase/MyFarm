package com.example.myfarm.ui.bydlo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfarm.Background;
import com.example.myfarm.Glowna;
import com.example.myfarm.R;
import com.example.myfarm.ui.zabiegi.Formzz;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Formzb extends AppCompatActivity {

    public static String data,data1,data2,data3;

    private CalendarView calender,calender1,calender2,calender3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_z_b);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        Log.i("id123456",id);


        EditText nr_zwierzecia = (EditText) findViewById(R.id.EditText1);
        EditText plec = (EditText) findViewById(R.id.EditText2);
        EditText kod_rasy = (EditText) findViewById(R.id.EditText3);
        EditText nr_matki = (EditText) findViewById(R.id.EditText4);
        EditText nr_ojca = (EditText) findViewById(R.id.EditText5);
        EditText kod_zdarzenia_p = (EditText) findViewById(R.id.EditText6);
        EditText dane_przybycia = (EditText) findViewById(R.id.EditText7);
        EditText kod_zdarzenia_u = (EditText) findViewById(R.id.EditText8);
        EditText dane_ubycia = (EditText) findViewById(R.id.EditText9);
        EditText dane_przewoznika = (EditText) findViewById(R.id.EditText10);

        nr_zwierzecia.setText(Glowna.nr_zwierzecia.get(Integer.valueOf(id)));
        plec.setText(Glowna.plec.get(Integer.valueOf(id)));
        kod_rasy.setText(Glowna.kod_rasy.get(Integer.valueOf(id)));
        nr_matki.setText(Glowna.nr_matki.get(Integer.valueOf(id)));
        nr_ojca.setText(Glowna.nr_ojca.get(Integer.valueOf(id)));
        kod_zdarzenia_p.setText(Glowna.kod_zdarzenia_p.get(Integer.valueOf(id)));
        dane_przybycia.setText(Glowna.dane_przybycia.get(Integer.valueOf(id)));
        kod_zdarzenia_u.setText(Glowna.kod_zdarzenia_u.get(Integer.valueOf(id)));
        dane_ubycia.setText(Glowna.dane_ubycia.get(Integer.valueOf(id)));
        dane_przewoznika.setText(Glowna.dane_przewoznika.get(Integer.valueOf(id)));


        calender = (CalendarView)findViewById(R.id.data_ur);

        //ROZBICIE FORMATU DATY I PRZYPISANIE JEJ DO KALENDARZA
        if (!Glowna.data_urodzenia.get(Integer.valueOf(id)).equals("")) {
            String date = Glowna.data_urodzenia.get(Integer.valueOf(id));
            String parts[] = date.split("-");

            int day = Integer.parseInt(parts[2]);
            int month = Integer.parseInt(parts[1]) - 1;
            int year = Integer.parseInt(parts[0]);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            long milliTime = calendar.getTimeInMillis();
            //USTAWIENIE ZMIENNEJ DO WYSŁANIA W RAZIE NIE ZMIENIANIA DATY PRZEZ UŻYTKOWNIKA
            //SPRAWDZENIE CZY FORMAT JEST OK
            if (month < 9) {
                Formzb.data = year + "-0" + (month + 1) + "-" + day;
                if (day < 10) {
                    Formzb.data = year + "-0" + (month + 1) + "-0" + day;
                }
            } else if (day < 10) {
                Formzb.data = year + "-" + (month + 1) + "-0" + day;
            }
            if ((month > 9) && (day > 10)) {
                Formzb.data = year + "-" + (month + 1) + "-" + day;
            }

            calender.setDate(milliTime, true, true);
        }else{
            data = format.format(calender.getDate());
        }
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Formzb.data =year+"-"+(month+1)+"-"+dayOfMonth;
            }
        });







        calender1 = (CalendarView)findViewById(R.id.data_oz);
        if (!Glowna.data_oznakowania.get(Integer.valueOf(id)).equals("")) {
            String date1 = Glowna.data_oznakowania.get(Integer.valueOf(id));
            String parts1[] = date1.split("-");

            int day1 = Integer.parseInt(parts1[2]);
            int month1 = Integer.parseInt(parts1[1])-1;
            int year1 = Integer.parseInt(parts1[0]);

            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.YEAR, year1);
            calendar1.set(Calendar.MONTH, month1);
            calendar1.set(Calendar.DAY_OF_MONTH, day1);

            long milliTime1 = calendar1.getTimeInMillis();
            //USTAWIENIE ZMIENNEJ DO WYSŁANIA W RAZIE NIE ZMIENIANIA DATY PRZEZ UŻYTKOWNIKA
            if (month1<9){
                Formzb.data1 =year1+"-0"+(month1+1)+"-"+day1;
                if(day1<10){
                    Formzb.data1 =year1+"-0"+(month1+1)+"-0"+day1;
                }
            }else if(day1<10){
                Formzb.data1 =year1+"-"+(month1+1)+"-0"+day1;
            }
            if((month1>9) && (day1>10)){
                Formzb.data1 =year1+"-"+(month1+1)+"-"+day1;
            }
            calender1.setDate(milliTime1, true, true);
        }else{
            data1 = format.format(calender1.getDate());
        }

        calender1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Formzb.data1 =year+"-"+(month+1)+"-"+dayOfMonth;
            }
        });


        calender2 = (CalendarView)findViewById(R.id.data_przy);
        if (!Glowna.data_przybycia.get(Integer.valueOf(id)).equals("")) {
            String date2 = Glowna.data_przybycia.get(Integer.valueOf(id));
            String parts2[] = date2.split("-");

            int day2 = Integer.parseInt(parts2[2]);
            int month2 = Integer.parseInt(parts2[1])-1;
            int year2 = Integer.parseInt(parts2[0]);

            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Calendar.YEAR, year2);
            calendar2.set(Calendar.MONTH, month2);
            calendar2.set(Calendar.DAY_OF_MONTH, day2);

            long milliTime2 = calendar2.getTimeInMillis();
            //USTAWIENIE ZMIENNEJ DO WYSŁANIA W RAZIE NIE ZMIENIANIA DATY PRZEZ UŻYTKOWNIKA
            if (month2<9){
                Formzb.data2 =year2+"-0"+(month2+1)+"-"+day2;
                if(day2<10){
                    Formzb.data2 =year2+"-0"+(month2+1)+"-0"+day2;
                }
            }else if(day2<10){
                Formzb.data2 =year2+"-"+(month2+1)+"-0"+day2;
            }
            if((month2>9) && (day2>10)){
                Formzb.data2 =year2+"-"+(month2+1)+"-"+day2;
            }
            calender2.setDate(milliTime2, true, true);
        }else{
            data2 = format.format(calender2.getDate());
        }

        calender2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Formzb.data2 =year+"-"+(month+1)+"-"+dayOfMonth;
            }
        });

        calender3 = (CalendarView)findViewById(R.id.data_ub);
        if (!Glowna.data_ubycia.get(Integer.valueOf(id)).equals("")) {
            String date3 = Glowna.data_ubycia.get(Integer.valueOf(id));
            String parts3[] = date3.split("-");

            int day3 = Integer.parseInt(parts3[2]);
            int month3 = Integer.parseInt(parts3[1])-1;
            int year3 = Integer.parseInt(parts3[0]);

            Calendar calendar3 = Calendar.getInstance();
            calendar3.set(Calendar.YEAR, year3);
            calendar3.set(Calendar.MONTH, month3);
            calendar3.set(Calendar.DAY_OF_MONTH, day3);

            long milliTime3 = calendar3.getTimeInMillis();
            //USTAWIENIE ZMIENNEJ DO WYSŁANIA W RAZIE NIE ZMIENIANIA DATY PRZEZ UŻYTKOWNIKA
            if (month3<9){
                Formzb.data3 =year3+"-0"+(month3+1)+"-"+day3;
                if(day3<10){
                    Formzb.data3 =year3+"-0"+(month3+1)+"-0"+day3;
                }
            }else if(day3<10){
                Formzb.data3 =year3+"-"+(month3+1)+"-0"+day3;
            }
            if((month3>9) && (day3>10)){
                Formzb.data3 =year3+"-"+(month3+1)+"-"+day3;
            }
            calender3.setDate(milliTime3, true, true);
        }else{
            data3 = format.format(calender3.getDate());
        }
        calender3.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Formzb.data3 =year+"-"+(month+1)+"-"+dayOfMonth;
            }
        });

    }

    public void FormA(View view) {
        EditText nr_zwierzecia = (EditText) findViewById(R.id.EditText1);
        EditText plec = (EditText) findViewById(R.id.EditText2);
        EditText kod_rasy = (EditText) findViewById(R.id.EditText3);
        EditText nr_matki = (EditText) findViewById(R.id.EditText4);
        EditText nr_ojca = (EditText) findViewById(R.id.EditText5);
        EditText kod_zdarzenia_p = (EditText) findViewById(R.id.EditText6);
        EditText dane_przybycia = (EditText) findViewById(R.id.EditText7);
        EditText kod_zdarzenia_u = (EditText) findViewById(R.id.EditText8);
        EditText dane_ubycia = (EditText) findViewById(R.id.EditText9);
        EditText dane_przewoznika = (EditText) findViewById(R.id.EditText10);

        String nr_zwierzecia_w = nr_zwierzecia.getText().toString();
        String data_ur_w = data;
        String plec_w = plec.getText().toString();
        String kod_rasy_w = kod_rasy.getText().toString();
        String data_oz_w = data1;
        String nr_matki_w = nr_matki.getText().toString();
        String nr_ojca_w = nr_ojca.getText().toString();
        String data_prz_w = data2;
        String kod_zdarzenia_p_w = kod_zdarzenia_p.getText().toString();
        String dane_przybycia_w = dane_przybycia.getText().toString();
        String data_ub_w = data3;
        String kod_zdarzenia_u_w = kod_zdarzenia_u.getText().toString();
        String dane_ubycia_w = dane_ubycia.getText().toString();
        String dane_przewoznika_w = dane_przewoznika.getText().toString();

        String user = String.valueOf(Background.id);


        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        String id_wpisu = Glowna.id_b.get(Integer.valueOf(id));

        Formzb_background sign = new Formzb_background(this);
        sign.execute(nr_zwierzecia_w, data_ur_w, plec_w, kod_rasy_w,data_oz_w,nr_matki_w,nr_ojca_w,data_prz_w,kod_zdarzenia_p_w,dane_przybycia_w,data_ub_w,kod_zdarzenia_u_w,dane_ubycia_w,dane_przewoznika_w,user,id_wpisu);
    }
}
