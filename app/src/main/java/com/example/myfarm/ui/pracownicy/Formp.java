package com.example.myfarm.ui.pracownicy;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfarm.Background;
import com.example.myfarm.R;
import com.example.myfarm.ui.alerty.Alerty;

public class Formp extends AppCompatActivity {

    Button _btnAddForm;
    EditText _txtNameF, _txtCategoryF, _txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_p);









    }

    public void FormA(View view) {
        EditText imie = (EditText) findViewById(R.id.EditText11);
        EditText nazwiski = (EditText) findViewById(R.id.EditText12);
        EditText tel = (EditText) findViewById(R.id.EditText18);
        EditText placa = (EditText) findViewById(R.id.EditText25);



        String imie_w = imie.getText().toString();
        String nazwiski_w = nazwiski.getText().toString();
        String tel_w = tel.getText().toString();
        String placa_w = placa.getText().toString();



        String user = String.valueOf(Background.id);

        Formp_background sign = new Formp_background(this);
        sign.execute(imie_w, nazwiski_w, tel_w, placa_w,user);
    }
}
