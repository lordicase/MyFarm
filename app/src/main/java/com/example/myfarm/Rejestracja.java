package com.example.myfarm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Rejestracja extends AppCompatActivity {

    EditText pas,pas2,emai,usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);

        usr=(EditText) findViewById(R.id.usernamer);
        pas=(EditText) findViewById(R.id.passwordr);
        pas2=(EditText) findViewById(R.id.password2r);
        emai=(EditText) findViewById(R.id.emailr);
    }

    public void Rejestracja(View view){
        String user=usr.getText().toString();
        String pass=pas.getText().toString();
        String pass2=pas2.getText().toString();
        String email=emai.getText().toString();

        Backgroundr bgr = new Backgroundr(this);
        bgr.execute(user,pass,pass2,email);

    }
}
