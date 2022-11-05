package com.example.myfarm.ui.glowna;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myfarm.R;

public class Ceny extends Fragment {
    TextView nazwa,cena;
    TableLayout tabela;
    TableRow tr;
    private GlownaViewModel glownaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        glownaViewModel =
                ViewModelProviders.of(this).get(GlownaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_glowna, container, false);

        tabela=(TableLayout) root.findViewById(R.id.tabela);
        //rozszeżanie kolumn???
        tabela.setColumnStretchable(0,true);
        tabela.setColumnStretchable(1,true);

        for (int i = 0; i< com.example.myfarm.Glowna.nazwac.size(); i++) {
            //dodawanie wiersza
            tr = new TableRow(getActivity());
            nazwa = new TextView(getActivity());
            cena = new TextView(getActivity());

            nazwa.setText(String.valueOf(com.example.myfarm.Glowna.nazwac.get(i)));
            nazwa.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            nazwa.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            nazwa.setBackgroundColor(Color.parseColor("#589E9090"));

            cena.setText(String.valueOf(com.example.myfarm.Glowna.cena.get(i)));
            cena.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            cena.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            tr.addView(nazwa);
            tr.addView(cena);

            tabela.addView(tr);

        }


        return root;
    }
}