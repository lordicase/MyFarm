package com.example.myfarm.ui.pracownicy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
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

import com.example.myfarm.Background;
import com.example.myfarm.Background_delete;
import com.example.myfarm.Glowna;
import com.example.myfarm.R;
import com.example.myfarm.ui.dzialki.Formzd;
import com.example.myfarm.ui.zabiegi.ZabiegiViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Pracownicy extends Fragment {

    TextView imie_p,nazwisko_p,telefon_p,placa_p;
    TableLayout tabela;
    TableRow tr;
    Button przycisk,przycisk2;
    private ZabiegiViewModel pracownicyViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pracownicyViewModel =
                ViewModelProviders.of(this).get(ZabiegiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pracownicy, container, false);

        //znalezienie tabeli
        tabela=(TableLayout) root.findViewById(R.id.tabela);
        //rozszeżanie kolumn???
        tabela.setColumnStretchable(0,true);
        tabela.setColumnStretchable(1,true);
        tabela.setColumnStretchable(2,true);
        tabela.setColumnStretchable(3,true);
        for (int i = 0; i< Glowna.id_p.size(); i++) {
            //dodawanie wiersza
            tr = new TableRow(getActivity());

            imie_p = new TextView(getActivity());
            nazwisko_p = new TextView(getActivity());
            telefon_p = new TextView(getActivity());
            placa_p = new TextView(getActivity());

            przycisk = new Button(getActivity());
            przycisk2 = new Button(getActivity());


            przycisk.setTag(Integer.valueOf(i));

            przycisk.setBackgroundResource(android.R.drawable.ic_menu_edit);
            przycisk.setLayoutParams (new TableRow.LayoutParams(100, 100));
            przycisk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = String.valueOf(v.getTag());
                    Intent formularz = new Intent(getActivity(), Formzp.class);
                    formularz.putExtra("id",id);
                    startActivity(formularz);
                }
            });

            przycisk2.setTag(Integer.valueOf(i));

            przycisk2.setBackgroundResource(android.R.drawable.ic_menu_delete);
            przycisk2.setLayoutParams (new TableRow.LayoutParams(100,100));
            przycisk2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {


                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle("Usuwanie wpisu");
                    alertDialog.setMessage("Czy chcesz usunąć wpis?");

                    alertDialog.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Glowna.page=5;

                            Background_delete sign = new Background_delete(getActivity());
                            sign.execute(Glowna.id_p.get((Integer) v.getTag()), String.valueOf(Background.id),"pracownicy");
                        }
                    });

                    alertDialog.setNegativeButton("NIE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    alertDialog.show();
                }
            });


            imie_p.setText(String.valueOf(Glowna.imie_p.get(i)));
            imie_p.setPadding(5,5,5,5);
            imie_p.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imie_p.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            imie_p.setBackgroundColor(Color.parseColor("#589E9090"));

            nazwisko_p.setText(String.valueOf(Glowna.nazwisko_p.get(i)));
            nazwisko_p.setPadding(5,5,5,5);
            nazwisko_p.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            nazwisko_p.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            telefon_p.setText(String.valueOf(Glowna.telefon_p.get(i)));
            telefon_p.setPadding(5,5,5,5);
            telefon_p.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            telefon_p.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            telefon_p.setBackgroundColor(Color.parseColor("#589E9090"));

            placa_p.setText(String.valueOf(Glowna.placa_p.get(i)));
            placa_p.setPadding(5,5,5,5);
            placa_p.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            placa_p.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            tr.addView(imie_p);
            tr.addView(nazwisko_p);
            tr.addView(telefon_p);
            tr.addView(placa_p);
            tr.addView(przycisk);
            tr.addView(przycisk2);
            tabela.addView(tr);
        }


        FloatingActionButton fab = root.findViewById(R.id.faba);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent formularz = new Intent(getActivity(), Formp.class);
                startActivity(formularz);
            }
        });

        return root;
    }
}

