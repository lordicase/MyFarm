package com.example.myfarm.ui.bydlo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfarm.Background;
import com.example.myfarm.R;
import com.example.myfarm.ui.alerty.Alerty;
import com.example.myfarm.ui.alerty.Form_background;
import com.example.myfarm.ui.zabiegi_o_r.Formzo;
import com.example.myfarm.ui.zabiegi_o_r.Formzo_background;

import java.text.SimpleDateFormat;

public class Formb extends AppCompatActivity {

    public static String data,data1,data2,data3;

    private CalendarView calender,calender1,calender2,calender3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_b);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        calender = (CalendarView)findViewById(R.id.data_ur);
        data = format.format(calender.getDate());
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Formb.data =year+"-"+(month+1)+"-"+dayOfMonth;
            }
        });



        calender1 = (CalendarView)findViewById(R.id.data_oz);
        data1 = format.format(calender1.getDate());
        calender1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Formb.data1 =year+"-"+(month+1)+"-"+dayOfMonth;
            }
        });


        calender2 = (CalendarView)findViewById(R.id.data_przy);
        data2 = format.format(calender2.getDate());
        calender2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Formb.data2 =year+"-"+(month+1)+"-"+dayOfMonth;
            }
        });


        calender3 = (CalendarView)findViewById(R.id.data_ub);
        data3 = format.format(calender3.getDate());
        calender3.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Formb.data3 =year+"-"+(month+1)+"-"+dayOfMonth;
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

        Formb_background sign = new Formb_background(this);
        sign.execute(nr_zwierzecia_w, data_ur_w, plec_w, kod_rasy_w,data_oz_w,nr_matki_w,nr_ojca_w,data_prz_w,kod_zdarzenia_p_w,dane_przybycia_w,data_ub_w,kod_zdarzenia_u_w,dane_ubycia_w,dane_przewoznika_w,user);
    }
}
