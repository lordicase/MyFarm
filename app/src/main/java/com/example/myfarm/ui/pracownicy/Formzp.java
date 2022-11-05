package com.example.myfarm.ui.pracownicy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfarm.Background;
import com.example.myfarm.Glowna;
import com.example.myfarm.R;

public class Formzp extends AppCompatActivity {

    Button _btnAddForm;
    EditText _txtNameF, _txtCategoryF, _txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_z_p);


        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");


        EditText imie = (EditText) findViewById(R.id.EditText11);
        EditText nazwisko = (EditText) findViewById(R.id.EditText12);
        EditText tel = (EditText) findViewById(R.id.EditText18);
        EditText placa = (EditText) findViewById(R.id.EditText25);

        imie.setText(Glowna.imie_p.get(Integer.valueOf(id)));
        nazwisko.setText(Glowna.nazwisko_p.get(Integer.valueOf(id)));
        tel.setText(Glowna.telefon_p.get(Integer.valueOf(id)));
        placa.setText(Glowna.placa_p.get(Integer.valueOf(id)));




    }

    public void FormA(View view) {
        EditText imie = (EditText) findViewById(R.id.EditText11);
        EditText nazwisko = (EditText) findViewById(R.id.EditText12);
        EditText tel = (EditText) findViewById(R.id.EditText18);
        EditText placa = (EditText) findViewById(R.id.EditText25);



        String imie_w = imie.getText().toString();
        String nazwiski_w = nazwisko.getText().toString();
        String tel_w = tel.getText().toString();
        String placa_w = placa.getText().toString();

        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        String id_wpisu = Glowna.id_p.get(Integer.valueOf(id));

        String user = String.valueOf(Background.id);

        Formzp_background sign = new Formzp_background(this);
        sign.execute(imie_w, nazwiski_w, tel_w, placa_w,user,id_wpisu);
    }
}
