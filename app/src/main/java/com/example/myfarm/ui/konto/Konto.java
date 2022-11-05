package com.example.myfarm.ui.konto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myfarm.Glowna;
import com.example.myfarm.MainActivity;
import com.example.myfarm.R;
import com.example.myfarm.Rejestracja;
import com.example.myfarm.ui.pracownicy.Formzp;
import com.example.myfarm.ui.zabiegi.ZabiegiViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.widget.ListPopupWindow.MATCH_PARENT;
import static android.widget.ListPopupWindow.WRAP_CONTENT;
import com.example.myfarm.Glowna;
import com.example.myfarm.R;

public class Konto extends Fragment {

    private KontoViewModel kontoViewModel;
    TextView email,nick;
    TableLayout tabela;
    TableRow tr;
    Button przycisk1, przycisk2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kontoViewModel =
                ViewModelProviders.of(this).get(KontoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_konto, container, false);




        nick = (TextView) root.findViewById(R.id.textView16);
        email = (TextView) root.findViewById(R.id.textView18);

        email.setText(Glowna.email);
        nick.setText(MainActivity.user);
        przycisk1 = (Button) root.findViewById(R.id.button);
        przycisk2= (Button) root.findViewById(R.id.button2);

        przycisk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Formze.class);
                startActivity(intent);
            }
        });

        przycisk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Formzh.class);
                startActivity(intent);
            }
        });

        return root;


    }



}