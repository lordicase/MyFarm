package com.example.myfarm.ui.konto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfarm.Background;
import com.example.myfarm.R;
import com.example.myfarm.ui.pracownicy.Formp_background;

import es.dmoral.toasty.Toasty;

public class Formzh extends AppCompatActivity {

    Button _btnAddForm;
    EditText _txtNameF, _txtCategoryF, _txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_z_h);


    }

    public void FormA(View view) {
        EditText haslo1 = (EditText) findViewById(R.id.EditText1);
        EditText haslo2 = (EditText) findViewById(R.id.EditText2);

        String haslo1_w = haslo1.getText().toString();
        String haslo2_w = haslo2.getText().toString();

        if (haslo1_w.equals(haslo2_w)) {

            String user = String.valueOf(Background.id);

            Formzh_background sign = new Formzh_background(this);
            sign.execute(haslo1_w,haslo2_w, user);
        } else {
            Toasty.error(getApplicationContext(), "Błąd, podane hasła nie są identyczne", Toast.LENGTH_LONG).show();

        }

    }
}
