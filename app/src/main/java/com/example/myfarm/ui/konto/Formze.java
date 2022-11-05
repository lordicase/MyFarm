package com.example.myfarm.ui.konto;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfarm.Background;
import com.example.myfarm.Glowna;
import com.example.myfarm.R;
import com.example.myfarm.ui.pracownicy.Formp_background;

import es.dmoral.toasty.Toasty;

public class Formze extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_z_e);









    }

    public void FormA(View view) {
        EditText email1 = (EditText) findViewById(R.id.EditText1);
        EditText email2 = (EditText) findViewById(R.id.EditText2);

        String email1_w = email1.getText().toString();
        String email2_w = email2.getText().toString();

        if(email1_w.equals(email2_w)){

            String user = String.valueOf(Background.id);

            Formze_background sign = new Formze_background(this);
            sign.execute(email1_w,user);
        }else{
            Toasty.error(getApplicationContext(), "Błąd, podane adresy nie są identyczne", Toast.LENGTH_LONG).show();

        }



    }
}
