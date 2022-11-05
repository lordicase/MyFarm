package com.example.myfarm.ui.alerty;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myfarm.Glowna;
import com.example.myfarm.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Alerty extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener {
    private boolean isFirstBackPressed = false;
    SupportMapFragment alertyFragment;
    public static String lati;
    public static String longi;
    public static int a;
    private GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View viev = inflater.inflate(R.layout.fragment_alerty, container, false);


        FloatingActionButton fab = viev.findViewById(R.id.faba);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Proszę zaznaczyć miejsce wystąpienia szkodnika", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                a++;
            }
        });


        alertyFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (alertyFragment == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            alertyFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, alertyFragment).commit();
        }
        alertyFragment.getMapAsync(this);
        return viev;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        int x= Glowna.latx.size();
        LatLng pl = new LatLng(Glowna.LAT,Glowna.LON);
        float zoomLevel = 10.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pl, zoomLevel));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        for (int i=0; i<x; i++) {
            LatLng mark = new LatLng(Glowna.latx.get(i), Glowna.lonx.get(i));
            if (Glowna.typ.get(i).equals("Zbożowe")) {
                mMap.addMarker(new MarkerOptions()
                        .position(mark)
                        .title(Glowna.nazwax.get(i))
                        .snippet(Glowna.typ.get(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.zielony)));
            }
            if (Glowna.typ.get(i).equals("Bobik")) {
                mMap.addMarker(new MarkerOptions()
                        .position(mark)
                        .title(Glowna.nazwax.get(i))
                        .snippet(Glowna.typ.get(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.fioletowy)));
            }
            if (Glowna.typ.get(i).equals("Łubin")) {
                mMap.addMarker(new MarkerOptions()
                        .position(mark)
                        .title(Glowna.nazwax.get(i))
                        .snippet(Glowna.typ.get(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.czerwony)));
            }
            if (Glowna.typ.get(i).equals("Groch")) {
                mMap.addMarker(new MarkerOptions()
                        .position(mark)
                        .title(Glowna.nazwax.get(i))
                        .snippet(Glowna.typ.get(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.blekitny)));
            }
            if (Glowna.typ.get(i)=="Kukurydza") {
                mMap.addMarker(new MarkerOptions()
                        .position(mark)
                        .title(Glowna.nazwax.get(i))
                        .snippet(Glowna.typ.get(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.niebieski)));
            }
            if (Glowna.typ.get(i).equals("Ziemniaki")) {
                mMap.addMarker(new MarkerOptions()
                        .position(mark)
                        .title(Glowna.nazwax.get(i))
                        .snippet(Glowna.typ.get(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pomaranczowy)));
            }
            if (Glowna.typ.get(i).equals("Buraki")) {
                mMap.addMarker(new MarkerOptions()
                        .position(mark)
                        .title(Glowna.nazwax.get(i))
                        .snippet(Glowna.typ.get(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.zolty)));
            }
            if (Glowna.typ.get(i).equals("Rzepak")) {
                mMap.addMarker(new MarkerOptions()
                        .position(mark)
                        .title(Glowna.nazwax.get(i))
                        .snippet(Glowna.typ.get(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.rozowy)));
            }
        }


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                if (a>=1) {
                    mMap.addMarker(new MarkerOptions().position(point));
                    a=0;
                    LatLng punkt = new LatLng(point.latitude, point.longitude);
                    float zoomLevel = 10.0f;
                    lati = String.valueOf(point.latitude);
                    longi = String.valueOf(point.longitude);
                    Intent formularz = new Intent(getActivity(), Form.class);
                    startActivity(formularz);
                }


            }

        });


    }

    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }
}