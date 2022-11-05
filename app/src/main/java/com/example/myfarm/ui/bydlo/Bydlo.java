package com.example.myfarm.ui.bydlo;

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
import android.widget.EditText;
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
import com.example.myfarm.ui.zabiegi.Formz;
import com.example.myfarm.ui.zabiegi.ZabiegiViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Bydlo extends Fragment {


    private ZabiegiViewModel bydloViewModel;
    TextView nr_zwierzecia,data_urodzenia,plec,kod_rasy,data_oznakowania,nr_matki,nr_ojca,data_przybycia,kod_zdarzenia_p,dane_przybycia,data_ubycia,kod_zdarzenia_u,dane_ubycia,dane_przewoznika;
    TableLayout tabela,tabela1,tabela2;
    TableRow tr,tr1,tr2,tr3,tr4;
    Button przycisk,przycisk2;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bydloViewModel =
                ViewModelProviders.of(this).get(ZabiegiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bydlo, container, false);

        //znalezienie tabeli
        tabela=(TableLayout) root.findViewById(R.id.tabela);

        tabela.setColumnStretchable(0,true);
        tabela.setColumnStretchable(1,true);
        tabela.setColumnStretchable(2,true);
        tabela.setColumnStretchable(3,true);
        tabela.setColumnStretchable(4,true);
        tabela.setColumnStretchable(5,true);
        tabela.setColumnStretchable(6,true);
        tabela.setColumnStretchable(7,true);


        for (int i = 0; i< Glowna.id_b.size(); i++) {


            tr = new TableRow(getActivity());
            tr1 = new TableRow(getActivity());
            tr2 = new TableRow(getActivity());
            tr3 = new TableRow(getActivity());
            tr4 = new TableRow(getActivity());

            tabela1 = new TableLayout(getActivity());
            tabela2 = new TableLayout(getActivity());


            nr_zwierzecia = new TextView(getActivity());
            data_urodzenia = new TextView(getActivity());
            plec = new TextView(getActivity());
            kod_rasy = new TextView(getActivity());
            data_oznakowania = new TextView(getActivity());
            nr_matki = new TextView(getActivity());
            nr_ojca = new TextView(getActivity());
            data_przybycia = new TextView(getActivity());
            kod_zdarzenia_p = new TextView(getActivity());
            dane_przybycia = new TextView(getActivity());
            data_ubycia = new TextView(getActivity());
            kod_zdarzenia_u = new TextView(getActivity());
            dane_ubycia = new TextView(getActivity());
            dane_przewoznika = new TextView(getActivity());

            przycisk = new Button(getActivity());
            przycisk2 = new Button(getActivity());


            przycisk.setTag(Integer.valueOf(i));

            przycisk.setBackgroundResource(android.R.drawable.ic_menu_edit);
            przycisk.setLayoutParams (new TableRow.LayoutParams(100, 100));
            przycisk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = String.valueOf(v.getTag());
                    Intent formularz = new Intent(getActivity(), Formzb.class);
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
                            Glowna.page=6;

                            Background_delete sign = new Background_delete(getActivity());
                            sign.execute(Glowna.id_b.get((Integer) v.getTag()), String.valueOf(Background.id),"dziennik");
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


            TextView nazwa_d = (TextView) root.findViewById(R.id.textView1);
            nr_zwierzecia.setText(String.valueOf(Glowna.nr_zwierzecia.get(i)));
            nr_zwierzecia.setPadding(5,5,5,5);
            nr_zwierzecia.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            nr_zwierzecia.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            nr_zwierzecia.setBackgroundColor(Color.parseColor("#589E9090"));

            data_urodzenia.setText(String.valueOf(Glowna.data_urodzenia.get(i)));
            data_urodzenia.setPadding(5,5,5,5);
            data_urodzenia.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            data_urodzenia.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            plec.setText(String.valueOf(Glowna.plec.get(i)));
            plec.setPadding(5,5,5,5);
            plec.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            plec.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            plec.setBackgroundColor(Color.parseColor("#589E9090"));

            kod_rasy.setText(String.valueOf(Glowna.kod_rasy.get(i)));
            kod_rasy.setPadding(5,5,5,5);
            kod_rasy.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            kod_rasy.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            data_oznakowania.setText(String.valueOf(Glowna.data_oznakowania.get(i)));
            data_oznakowania.setPadding(5,5,5,5);
            data_oznakowania.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            data_oznakowania.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            data_oznakowania.setBackgroundColor(Color.parseColor("#589E9090"));

            nr_matki.setText(String.valueOf(Glowna.nr_matki.get(i)));
            nr_matki.setPadding(5,5,5,5);
            nr_matki.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            nr_matki.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            nr_ojca.setText(String.valueOf(Glowna.nr_ojca.get(i)));
            nr_ojca.setPadding(5,5,5,5);
            nr_ojca.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            nr_ojca.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            data_przybycia.setText(String.valueOf(Glowna.data_przybycia.get(i)));
            data_przybycia.setPadding(5,5,5,5);
            data_przybycia.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            data_przybycia.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            data_przybycia.setBackgroundColor(Color.parseColor("#589E9090"));

            kod_zdarzenia_p.setText(String.valueOf(Glowna.kod_zdarzenia_p.get(i)));
            kod_zdarzenia_p.setPadding(5,5,5,5);
            kod_zdarzenia_p.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            kod_zdarzenia_p.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            kod_zdarzenia_p.setBackgroundColor(Color.parseColor("#589E9090"));

            dane_przybycia.setText(String.valueOf(Glowna.dane_przybycia.get(i)));
            dane_przybycia.setPadding(5,5,5,5);
            dane_przybycia.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            dane_przybycia.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            data_ubycia.setText(String.valueOf(Glowna.data_ubycia.get(i)));
            data_ubycia.setPadding(5,5,5,5);
            data_ubycia.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            data_ubycia.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            data_ubycia.setBackgroundColor(Color.parseColor("#589E9090"));

            kod_zdarzenia_u.setText(String.valueOf(Glowna.kod_zdarzenia_u.get(i)));
            kod_zdarzenia_u.setPadding(5,5,5,5);
            kod_zdarzenia_u.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            kod_zdarzenia_u.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
            kod_zdarzenia_u.setBackgroundColor(Color.parseColor("#589E9090"));

            dane_ubycia.setText(String.valueOf(Glowna.dane_ubycia.get(i)));
            dane_ubycia.setPadding(5,5,5,5);
            dane_ubycia.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            dane_ubycia.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);

            dane_przewoznika.setText(String.valueOf(Glowna.dane_przewoznika.get(i)));
            dane_przewoznika.setPadding(5,5,5,5);
            dane_przewoznika.setLayoutParams (new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            dane_przewoznika.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);





            tr.addView(nr_zwierzecia);
            tr.addView(data_urodzenia);
            tr.addView(plec);
            tr.addView(kod_rasy);
            tr.addView(data_oznakowania);
            tr1.addView(nr_matki);
            tr1.addView(data_przybycia);
            tr2.addView(nr_ojca);
            tr2.addView(kod_zdarzenia_p);
            tabela1.addView(tr1);
            tabela1.addView(tr2);
            tr.addView(tabela1);
            tr.addView(dane_przybycia);
            tr3.addView(data_ubycia);
            tr3.addView(dane_ubycia);
            tr4.addView(kod_zdarzenia_u);
            tr4.addView(dane_przewoznika);
            tabela2.addView(tr3);
            tabela2.addView(tr4);
            tr.addView(tabela2);
            tr.addView(przycisk);
            tr.addView(przycisk2);
            tabela.addView(tr);

            nr_zwierzecia.setLayoutParams(new TableRow.LayoutParams(-1, -1));
            data_urodzenia.setLayoutParams(new TableRow.LayoutParams(-1, -1));
            plec.setLayoutParams(new TableRow.LayoutParams(-1, -1));
            kod_rasy.setLayoutParams(new TableRow.LayoutParams(-1, -1));
            data_oznakowania.setLayoutParams(new TableRow.LayoutParams(-1, -1));
            dane_przybycia.setLayoutParams(new TableRow.LayoutParams(-1, -1));

            nr_matki.setWidth(400);
            nr_ojca.setWidth(400);
            data_przybycia.setWidth(350);
            kod_zdarzenia_p.setWidth(350);
            data_ubycia.setWidth(350);
            kod_zdarzenia_u.setWidth(350);
        }

        FloatingActionButton fab = root.findViewById(R.id.faba);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent formularz = new Intent(getActivity(), Formb.class);
                startActivity(formularz);
            }
        });


        return root;
    }
}
