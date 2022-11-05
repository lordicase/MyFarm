package com.example.myfarm.ui.dzialki;

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

public class Formd extends AppCompatActivity {

    Button _btnAddForm;
    EditText _txtNameF, _txtCategoryF, _txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_d);




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


        String user = String.valueOf(Background.id);

        Formd_background sign = new Formd_background(this);
        sign.execute(numer_e_d_w, nazwa_w_w, pow_w, uprawa_w,lokalizacja_w,user);
    }
}
