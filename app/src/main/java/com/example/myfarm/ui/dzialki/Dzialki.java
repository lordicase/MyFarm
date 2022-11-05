package com.example.myfarm.ui.dzialki;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myfarm.Glowna;
import com.example.myfarm.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.util.Arrays;
import java.util.List;

public class Dzialki extends Fragment implements  OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener  {

   // private DzialkiViewModel dzialkiViewModel;

    SupportMapFragment mapFragment;

   private GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View viev= inflater.inflate(R.layout.fragment_dzialki, container, false);
        mapFragment=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null){
            FragmentManager fm= getFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return viev;
    }



    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng pl = new LatLng(Glowna.LAT,Glowna.LON);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        float zoomLevel = 10.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pl, zoomLevel));


        int liczba_dzialek = Glowna.ile_znacz.size();

        int suma_znacznikow = 0;
        int licznik=0;
        for (int i=0; i<liczba_dzialek; i++) {

            PolygonOptions rectOptions = new PolygonOptions().clickable(true);

            int liczba_znacznikow = Glowna.ile_znacz.get(i);

               suma_znacznikow+=liczba_znacznikow;
            for (int y=licznik; y<suma_znacznikow; y++) {
                licznik++;

                rectOptions.add(new LatLng(Glowna.lat.get(y), Glowna.lng.get(y)));
            }




            rectOptions.strokeColor(Color.rgb(255,0,0)).fillColor(0x40ff0000);

            Polygon polygon = mMap.addPolygon(rectOptions);
            polygon.setTag("Nazwa działki: "+Glowna.nazwax1.get(i)+"\n Uprawa: "+Glowna.uprawa.get(i));


        }

        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
    }

    @Override
    public void onPolygonClick(Polygon polygon) {


        Toast.makeText(getActivity(),polygon.getTag().toString(),Toast.LENGTH_LONG).show();

    }


    @Override
    public void onPolylineClick(Polyline polyline) {
        // Flip from solid stroke to dotted stroke pattern.


    }
}


