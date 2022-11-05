package com.example.myfarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText pas,usr;
    public static String user;
     String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usr=(EditText) findViewById(R.id.username);
        pas=(EditText) findViewById(R.id.password);
    }

    public void loginBtn(View view){
         user=usr.getText().toString();
         pass=pas.getText().toString();




        Background bg = new Background(this);
        bg.execute(user,pass);

    }

    public void register(View view) {
        Glowna.page=0;
        Intent intent = new Intent();
        intent.setClass(this, Rejestracja.class);
        startActivity(intent);
    }


}
