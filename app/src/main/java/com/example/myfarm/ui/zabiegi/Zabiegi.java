package com.example.myfarm.ui.zabiegi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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

import com.example.myfarm.Background;
import com.example.myfarm.Background_delete;
import com.example.myfarm.Glowna;
import com.example.myfarm.R;
import com.example.myfarm.ui.dzialki.Formzd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Zabiegi extends Fragment {

    private ZabiegiViewModel zabiegiViewModel;
    TextView data,nazwa_d,typ_z,opis;
    TableLayout tabela;
    TableRow tr;
    Button przycisk,przycisk2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        zabiegiViewModel =
                ViewModelProviders.of(this).get(ZabiegiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_zabiegi, container, false);

        //znalezienie tabeli
        tabela=(TableLayout) root.findViewById(R.id.tabela);
        //rozszeżanie kolumn???
        tabela.setColumnStretchable(0,true);
        tabela.setColumnStretchable(1,true);
        tabela.setColumnStretchable(2,true);
        tabela.setColumnStretchable(3,true);
        for (int i=0;i<Glowna.ilosc_zabiegow;i++) {
            //dodawanie wiersza
            tr = new TableRow(getActivity());
            data = new TextView(getActivity());
            nazwa_d = new TextView(getActivity());
            typ_z = new TextView(getActivity());
            opis = new TextView(getActivity());

            przycisk = new Button(getActivity());
            przycisk2 = new Button(getActivity());


            przycisk.setTag(Integer.valueOf(i));

            przycisk.setBackgroundResource(android.R.drawable.ic_menu_edit);
            przycisk.setLayoutParams (new TableRow.LayoutParams(100, 100));
            przycisk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = String.valueOf(v.getTag());
                    Intent formularz = new Intent(getActivity(), Formzz.class);
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
                            Glowna.page=2;

                            Background_delete sign = new Background_delete(getActivity());
                            sign.execute(Glowna.id_z.get((Integer) v.getTag()), String.valueOf(Background.id),"zabiegi");
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


            data.setText(String.valueOf(Glowna.data_z.get(i)));
            data.setPadding(5,5,5,5);
            data.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            data.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            data.setBackgroundColor(Color.parseColor("#589E9090"));

            nazwa_d.setText(String.valueOf(Glowna.nazwa_w_d_z.get(i)));
            nazwa_d.setPadding(5,5,5,5);
            nazwa_d.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            nazwa_d.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            typ_z.setText(String.valueOf(Glowna.typ_z.get(i)));
            typ_z.setPadding(5,5,5,5);
            typ_z.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            typ_z.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            typ_z.setBackgroundColor(Color.parseColor("#589E9090"));

            opis.setText(String.valueOf(Glowna.opis_z.get(i)));
            opis.setPadding(5,5,5,5);
            opis.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            opis.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            tr.addView(data);
            tr.addView(nazwa_d);
            tr.addView(typ_z);
            tr.addView(opis);
            tr.addView(przycisk);
            tr.addView(przycisk2);
            tabela.addView(tr);
        }

        FloatingActionButton fab = root.findViewById(R.id.faba);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent formularz = new Intent(getActivity(), Formz.class);
                startActivity(formularz);
            }
        });


        return root;
    }
}