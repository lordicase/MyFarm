package com.example.myfarm.ui.dzialki;

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

public class Formzd extends AppCompatActivity {

    Button _btnAddForm;
    EditText _txtNameF, _txtCategoryF, _txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_z_d);


        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");


        EditText numer_e_d = (EditText) findViewById(R.id.EditText11);
        EditText nazwa_w = (EditText) findViewById(R.id.EditText12);
        EditText pow = (EditText) findViewById(R.id.EditText18);
        EditText uprawa = (EditText) findViewById(R.id.EditText25);
        EditText lokalizacja = (EditText) findViewById(R.id.EditText26);


        numer_e_d.setText(Glowna.nr_e_d.get(Integer.valueOf(id)));
        nazwa_w.setText(Glowna.nazwa_w.get(Integer.valueOf(id)));
        pow.setText(Glowna.pow.get(Integer.valueOf(id)));
        uprawa.setText(Glowna.uprawa_d.get(Integer.valueOf(id)));
        lokalizacja.setText(Glowna.adres.get(Integer.valueOf(id)));

    }

    public void FormA(View view) {
        EditText numer_e_d = (EditText) findViewById(R.id.EditText11);
        EditText nazwa_w = (EditText) findViewById(R.id.EditText12);
        EditText pow = (EditText) findViewById(R.id.EditText18);
        EditText uprawa = (EditText) findViewById(R.id.EditText25);
        EditText lokalizacja = (EditText) findViewById(R.id.EditText26);


        String numer_e_d_w = numer_e_d.getText().toString();
        String nazwa_w_w = nazwa_w.getText().toString();
        String pow_w = pow.getText().toString();
        String uprawa_w = uprawa.getText().toString();
        String lokalizacja_w = lokalizacja.getText().toString();

        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        String id_wpisu = Glowna.id_d.get(Integer.valueOf(id));

        String user = String.valueOf(Background.id);

        Formzd_background sign = new Formzd_background(this);
        sign.execute(numer_e_d_w, nazwa_w_w, pow_w, uprawa_w,lokalizacja_w,user,id_wpisu);
    }
}
