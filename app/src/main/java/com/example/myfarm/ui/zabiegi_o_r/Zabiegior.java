package com.example.myfarm.ui.zabiegi_o_r;

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

public class Zabiegior extends Fragment {
    TextView nazwa_w_d_z_o_r,uprawa_z_o_r,pow_z_o_r,data_z_o_r,nazwa_s_z_o_r,dawka_z_o_r,uwagi_z_o_r;
    TableLayout tabela;
    TableRow tr;
    Button przycisk,przycisk2;
    private ZabiegiViewModel zabiegi_o_r_ViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        zabiegi_o_r_ViewModel =
                ViewModelProviders.of(this).get(ZabiegiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_z_o_r, container, false);

        //znalezienie tabeli
        tabela=(TableLayout) root.findViewById(R.id.tabela);
        //rozszeżanie kolumn???
        tabela.setColumnStretchable(0,true);
        tabela.setColumnStretchable(1,true);
        tabela.setColumnStretchable(2,true);
        tabela.setColumnStretchable(3,true);
        tabela.setColumnStretchable(4,true);
        tabela.setColumnStretchable(5,true);
        tabela.setColumnStretchable(6,true);

        for (int i = 0; i< Glowna.id_z_o_r.size(); i++) {
            //dodawanie wiersza
            tr = new TableRow(getActivity());

            nazwa_w_d_z_o_r = new TextView(getActivity());
            uprawa_z_o_r = new TextView(getActivity());
            pow_z_o_r = new TextView(getActivity());
            data_z_o_r = new TextView(getActivity());
            nazwa_s_z_o_r = new TextView(getActivity());
            dawka_z_o_r = new TextView(getActivity());
            uwagi_z_o_r = new TextView(getActivity());

            przycisk = new Button(getActivity());
            przycisk2 = new Button(getActivity());


            przycisk.setTag(Integer.valueOf(i));

            przycisk.setBackgroundResource(android.R.drawable.ic_menu_edit);
            przycisk.setLayoutParams (new TableRow.LayoutParams(100, 100));
            przycisk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = String.valueOf(v.getTag());
                    Intent formularz = new Intent(getActivity(), Formzzo.class);
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
                            Glowna.page=3;

                            Background_delete sign = new Background_delete(getActivity());
                            sign.execute(Glowna.id_z_o_r.get((Integer) v.getTag()), String.valueOf(Background.id),"zabiegi_o_r");
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



            nazwa_w_d_z_o_r.setText(String.valueOf(Glowna.nazwa_w_d_z_o_r.get(i)));
            nazwa_w_d_z_o_r.setPadding(5,5,5,5);
            nazwa_w_d_z_o_r.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            nazwa_w_d_z_o_r.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            nazwa_w_d_z_o_r.setBackgroundColor(Color.parseColor("#589E9090"));

            uprawa_z_o_r.setText(String.valueOf(Glowna.uprawa_z_o_r.get(i)));
            uprawa_z_o_r.setPadding(5,5,5,5);
            uprawa_z_o_r.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            uprawa_z_o_r.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            pow_z_o_r.setText(String.valueOf(Glowna.pow_z_o_r.get(i)));
            pow_z_o_r.setPadding(5,5,5,5);
            pow_z_o_r.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            pow_z_o_r.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            pow_z_o_r.setBackgroundColor(Color.parseColor("#589E9090"));

            data_z_o_r.setText(String.valueOf(Glowna.data_z_o_r.get(i)));
            data_z_o_r.setPadding(5,5,5,5);
            data_z_o_r.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            data_z_o_r.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            nazwa_s_z_o_r.setText(String.valueOf(Glowna.nazwa_s_z_o_r.get(i)));
            nazwa_s_z_o_r.setPadding(5,5,5,5);
            nazwa_s_z_o_r.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            nazwa_s_z_o_r.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            nazwa_s_z_o_r.setBackgroundColor(Color.parseColor("#589E9090"));

            dawka_z_o_r.setText(String.valueOf(Glowna.dawka_z_o_r.get(i)));
            dawka_z_o_r.setPadding(5,5,5,5);
            dawka_z_o_r.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            dawka_z_o_r.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            uwagi_z_o_r.setText(String.valueOf(Glowna.uwagi_z_o_r.get(i)));
            uwagi_z_o_r.setPadding(5,5,5,5);
            uwagi_z_o_r.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            uwagi_z_o_r.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            uwagi_z_o_r.setBackgroundColor(Color.parseColor("#589E9090"));

            tr.addView(nazwa_w_d_z_o_r);
            tr.addView(uprawa_z_o_r);
            tr.addView(pow_z_o_r);
            tr.addView(data_z_o_r);
            tr.addView(nazwa_s_z_o_r);
            tr.addView(dawka_z_o_r);
            tr.addView(uwagi_z_o_r);
            tr.addView(przycisk);
            tr.addView(przycisk2);
            tabela.addView(tr);
        }

        FloatingActionButton fab = root.findViewById(R.id.faba);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent formularz = new Intent(getActivity(), Formzo.class);
                startActivity(formularz);
            }
        });



        return root;
    }
}

