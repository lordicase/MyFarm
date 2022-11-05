package com.example.myfarm;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.provider.Telephony.Mms.Part.TEXT;

public class Glowna extends AppCompatActivity {

    private static final String SHARED_PREFS = "sharedPrefs";
    private String TEXTu;
    private long backPressedTime;
    public static int page;

    private ProgressBar spinner;


    //lokalizacja urządzenia
    public static double LAT;
    public static double LON;

    public int ile_znacznikow;
    public static int x=Background.id;
    public static String email;
    public static String nick;



    // Tag name for log data.
    private static final String TAG_XML_PULL_PARSER = "XML_PULL_PARSER";

    // Message command for activity handler to show xml parse result.
    private static final int MESSAGE_SHOW_XML_PARSE_RESULT = 1;

    // Message data bundle key to save xml parsed result.
    private static final String KEY_XML_PARSE_RESULT = "KEY_XML_PARSE_RESULT";

    // Parse xml use XmlPullParser button.
    private Button parseXmlUsePullButton = null;

    // Display xml parse result text view.
    private TextView showXmlParseResultTextView = null;

    // This handler is waiting for child thread message to display xml parse result in text view.
    private Handler showParseResultHandler = null;

    // OkHttpClient to read xml file from url.
    private OkHttpClient okHttpClient = null;



    public String xmlParseResult ="5";

    //LINK DO POBIERANIA DANYCH
    private String xmlFileUrl2 = "https://mzsk.pl/MYFARM/mobilna/pobieranie_danych.php?id="+x;

    //DANE ALERTOW
    public static ArrayList<String> nazwax = new ArrayList<>();
    public static ArrayList<String> typ = new ArrayList<>();
    public static ArrayList<Double> lonx = new ArrayList<>();
    public static ArrayList<Double> latx = new ArrayList<>();

    //DANE OBRYSOW DZIALEK
    public static ArrayList<String> nazwax1 = new ArrayList<>();
    public static ArrayList<String> uprawa = new ArrayList<>();
    public static ArrayList<Double> lat = new ArrayList<>();
    public static ArrayList<Double> lng = new ArrayList<>();
    public static ArrayList<Integer> ile_znacz = new ArrayList<>();

    //DANE ZABIEGOW
    public static int ilosc_zabiegow =0;
    public static ArrayList<String> id_z = new ArrayList<>();
    public static ArrayList<String> data_z = new ArrayList<>();
    public static ArrayList<String> typ_z = new ArrayList<>();
    public static ArrayList<String> opis_z = new ArrayList<>();
    public static ArrayList<String> nazwa_w_d_z = new ArrayList<>();

    //DANE ZABIEGOW OCHRONY ROSLIN
    public static ArrayList<String> id_z_o_r = new ArrayList<>();
    public static ArrayList<String> nazwa_w_d_z_o_r = new ArrayList<>();
    public static ArrayList<String> uprawa_z_o_r = new ArrayList<>();
    public static ArrayList<String> pow_z_o_r = new ArrayList<>();
    public static ArrayList<String> data_z_o_r = new ArrayList<>();
    public static ArrayList<String> nazwa_s_z_o_r = new ArrayList<>();
    public static ArrayList<String> dawka_z_o_r = new ArrayList<>();
    public static ArrayList<String> uwagi_z_o_r = new ArrayList<>();

    // DANE CEN
    public static ArrayList<String> nazwac = new ArrayList<>();
    public static ArrayList<String> cena = new ArrayList<>();


    //DANE DZIALEK
    public static ArrayList<String> id_d = new ArrayList<>();
    public static ArrayList<String> nr_e_d = new ArrayList<>();
    public static ArrayList<String> nazwa_w = new ArrayList<>();
    public static ArrayList<String> pow = new ArrayList<>();
    public static ArrayList<String> adres = new ArrayList<>();
    public static ArrayList<String> uprawa_d = new ArrayList<>();


    //DANE PRACOWNIKOW
    public static ArrayList<String> id_p = new ArrayList<>();
    public static ArrayList<String> imie_p = new ArrayList<>();
    public static ArrayList<String> nazwisko_p = new ArrayList<>();
    public static ArrayList<String> telefon_p = new ArrayList<>();
    public static ArrayList<String> placa_p = new ArrayList<>();

    //DANE BYDŁA
    public static ArrayList<String> id_b = new ArrayList<>();
    public static ArrayList<String> nr_zwierzecia = new ArrayList<>();
    public static ArrayList<String> data_urodzenia = new ArrayList<>();
    public static ArrayList<String> plec = new ArrayList<>();
    public static ArrayList<String> kod_rasy = new ArrayList<>();
    public static ArrayList<String> data_oznakowania = new ArrayList<>();
    public static ArrayList<String> nr_matki = new ArrayList<>();
    public static ArrayList<String> nr_ojca = new ArrayList<>();
    public static ArrayList<String> data_przybycia = new ArrayList<>();
    public static ArrayList<String> kod_zdarzenia_p = new ArrayList<>();
    public static ArrayList<String> dane_przybycia = new ArrayList<>();
    public static ArrayList<String> data_ubycia = new ArrayList<>();
    public static ArrayList<String> kod_zdarzenia_u = new ArrayList<>();
    public static ArrayList<String> dane_ubycia = new ArrayList<>();
    public static ArrayList<String> dane_przewoznika = new ArrayList<>();
    public static ArrayList<String> uwagi = new ArrayList<>();

    private AppBarConfiguration mAppBarConfiguration;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_glowna);

        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //DANE ALERTOW
        nazwax = new ArrayList<>();
        typ = new ArrayList<>();
        lonx = new ArrayList<>();
        latx = new ArrayList<>();

        //DANE OBRYSOW DZIALEK
        nazwax1 = new ArrayList<>();
        uprawa = new ArrayList<>();
        lat = new ArrayList<>();
        lng = new ArrayList<>();
        ile_znacz = new ArrayList<>();

        //DANE ZABIEGOW
        ilosc_zabiegow =0;
        id_z = new ArrayList<>();
        data_z = new ArrayList<>();
        typ_z = new ArrayList<>();
        opis_z = new ArrayList<>();
        nazwa_w_d_z = new ArrayList<>();

        //DANE ZABIEGOW OCHRONY ROSLIN
        id_z_o_r = new ArrayList<>();
        nazwa_w_d_z_o_r = new ArrayList<>();
        uprawa_z_o_r = new ArrayList<>();
        pow_z_o_r = new ArrayList<>();
        data_z_o_r = new ArrayList<>();
        nazwa_s_z_o_r = new ArrayList<>();
        dawka_z_o_r = new ArrayList<>();
        uwagi_z_o_r = new ArrayList<>();

        // DANE CEN
        nazwac = new ArrayList<>();
        cena = new ArrayList<>();


        //DANE DZIALEK
        id_d = new  ArrayList<>();
        nr_e_d = new ArrayList<>();
        nazwa_w = new ArrayList<>();
        pow = new ArrayList<>();
        adres = new ArrayList<>();
        uprawa_d = new ArrayList<>();


        //DANE PRACOWNIKOW
        id_p = new ArrayList<>();
        imie_p = new ArrayList<>();
        nazwisko_p = new ArrayList<>();
        telefon_p = new ArrayList<>();
        placa_p = new ArrayList<>();


        //DANE BYDŁA
         id_b = new ArrayList<>();
         nr_zwierzecia = new ArrayList<>();
         data_urodzenia = new ArrayList<>();
         plec = new ArrayList<>();
         kod_rasy = new ArrayList<>();
         data_oznakowania = new ArrayList<>();
         nr_matki = new ArrayList<>();
         nr_ojca = new ArrayList<>();
         data_przybycia = new ArrayList<>();
         kod_zdarzenia_p = new ArrayList<>();
         dane_przybycia = new ArrayList<>();
         data_ubycia = new ArrayList<>();
         kod_zdarzenia_u = new ArrayList<>();
         dane_ubycia = new ArrayList<>();
         dane_przewoznika = new ArrayList<>();
         uwagi = new ArrayList<>();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_glowna,    R.id.nav_dzialki, R.id.nav_zabiegi,
                R.id.nav_alerty,    R.id.nav_pogoda, R.id.nav_konto,
                R.id.nav_zabiegior, R.id.nav_dzialkiw, R.id.nav_dzialkiw,
                R.id.nav_bydlo, R.id.nav_pracownicy)
                .setDrawerLayout(drawer)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        getLastLocation();

        // Init all ui controls first.
        initControls();




        //POBIERANIE DANYCH
        if(URLUtil.isHttpUrl(xmlFileUrl2) || URLUtil.isHttpsUrl(xmlFileUrl2)) {
            // Create a OkHttpClient request builder.
            Request.Builder builder2 = new Request.Builder();

            // Set xml file url.
            builder2  = builder2.url(xmlFileUrl2);

            // Build http request.
            Request request1 = builder2.build();

            // Create a OkHttp3 Call object.
            Call call1 = okHttpClient.newCall(request1);

            // Execute the get xml file request asynchronously in an automate child thread.
            //alerty
            call1.enqueue(new Callback() {


                public void onFailure(Call call1, IOException e) {
                    sendXmlParseResultToActivityHandler(e.getMessage());
                }


                public void onResponse(Call call1, Response response) throws IOException {
                    if(response.isSuccessful())
                    {
                        String resultXml = response.body().string();

                        xmlParseResult = parseXmlUsePullParser2(resultXml);


                    }
                }
            });
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                switch (page) {
                    case 0:
                        navController.navigate(R.id.nav_glowna);
                        spinner.setVisibility(View.GONE);
                        break;
                    case 1:
                        navController.navigate(R.id.nav_dzialkiw);
                        spinner.setVisibility(View.GONE);
                        break;
                    case 2:
                        navController.navigate(R.id.nav_zabiegi);
                        spinner.setVisibility(View.GONE);
                        break;
                    case 3:
                        navController.navigate(R.id.nav_zabiegior);
                        spinner.setVisibility(View.GONE);
                        break;
                    case 4:
                        navController.navigate(R.id.nav_alerty);
                        spinner.setVisibility(View.GONE);
                        break;
                    case 5:
                        navController.navigate(R.id.nav_pracownicy);
                        spinner.setVisibility(View.GONE);
                        break;
                    case 6:
                        navController.navigate(R.id.nav_bydlo);
                        spinner.setVisibility(View.GONE);
                        break;
                    case 7:
                        navController.navigate(R.id.nav_konto);
                        spinner.setVisibility(View.GONE);
                        break;

                }
            }
        }, 3000);



    }



    /* Send message to activity main thread Handler to display xml parse result. */
    private void sendXmlParseResultToActivityHandler(String xmlParseResult)
    {
        // Create message object.
        Message msg = new Message();
        msg.what = MESSAGE_SHOW_XML_PARSE_RESULT;

        // Add error message in message object data.
        Bundle bundle = new Bundle();
        bundle.putString(KEY_XML_PARSE_RESULT, xmlParseResult);
        msg.setData(bundle);

        // Send message to activity ui update Handler.
        showParseResultHandler.sendMessage(msg);
    }







    //ANALIZA PLIKU XML
    private String parseXmlUsePullParser2(String xmlString)
    {
        StringBuffer retBuf = new StringBuffer();

        try {
            // Create xml pull parser factory.
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();

            // Create XmlPullParser.
            XmlPullParser xmlPullParser = parserFactory.newPullParser();

            // Create a new StringReader.
            StringReader xmlStringReader = new StringReader(xmlString);

            // Set the string reader as XmlPullParser input.
            xmlPullParser.setInput(xmlStringReader);

            // Get event type during xml parse.
            int eventType = xmlPullParser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {
                // Get xml element node name.
                String nodeName = xmlPullParser.getName();


                //POBIERANIE WPIÓW ZABIEGÓW
                if (!TextUtils.isEmpty(nodeName)) {
                    if (eventType == XmlPullParser.START_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "Start element " + nodeName);

                        if ("zabieg".equalsIgnoreCase(nodeName)){
                            id_z.add(xmlPullParser.getAttributeValue(null, "id"));
                            data_z.add(xmlPullParser.getAttributeValue(null, "data"));
                            typ_z.add(xmlPullParser.getAttributeValue(null, "typ_z"));
                            opis_z.add(xmlPullParser.getAttributeValue(null, "opis"));
                            nazwa_w_d_z.add(xmlPullParser.getAttributeValue(null, "nazwa_w_d"));
                            ilosc_zabiegow++;
                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "End element " + nodeName);


                        if("zabieg".equalsIgnoreCase(nodeName))
                        {

                        }
                    }
                }





                //POBIERANIE ZNACZNIKÓW ALERTÓW
                if (!TextUtils.isEmpty(nodeName)) {
                    if (eventType == XmlPullParser.START_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "Start element " + nodeName);

                        if ("marker".equalsIgnoreCase(nodeName)){
                            String ID = xmlPullParser.getAttributeValue(null, "id");
                            nazwax.add(xmlPullParser.getAttributeValue(null, "name"));
                            String adres = xmlPullParser.getAttributeValue(null, "address");
                            typ.add(xmlPullParser.getAttributeValue(null, "type"));
                            latx.add(Double.parseDouble(xmlPullParser.getAttributeValue(null, "lat")));
                            lonx.add(Double.parseDouble(xmlPullParser.getAttributeValue(null, "lng")));

                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

                            TEXTu = sharedPreferences.getString("TEXT", "0");
                            if (Integer.valueOf(ID)>Integer.valueOf(TEXTu)) {
                                if ((Double.parseDouble(xmlPullParser.getAttributeValue(null, "lat"))<LAT+0.09090909)&&(Double.parseDouble(xmlPullParser.getAttributeValue(null, "lat"))>LAT-0.09090909)&&
                                        (Double.parseDouble(xmlPullParser.getAttributeValue(null, "lng"))<LON+0.145914439)&&(Double.parseDouble(xmlPullParser.getAttributeValue(null, "lng"))>LON-0.145914439)) {
                                    //POWIADOMIENIA
                                    NotificationManager mNotificationManager =
                                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                        NotificationChannel channel = new NotificationChannel("1",
                                                "CHANNEL_NAME",
                                                NotificationManager.IMPORTANCE_DEFAULT);
                                        channel.setDescription("NOTIFICATION_CHANNEL_DESCRIPTION");
                                        mNotificationManager.createNotificationChannel(channel);
                                    }
                                    // Builds notification
                                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                                            .setSmallIcon(R.drawable.ic_alerty)
                                            .setContentTitle("Nowy alert")
                                            .setContentText("Wykryto nowy alert, sprawdź mapę szkodników!");

                                    // Creates the intent needed to show the notification
                                    Intent notificationIntent = new Intent(this, MainActivity.class);
                                    PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                    builder.setContentIntent(contentIntent);

                                    // Add as notification
                                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                    manager.notify(0, builder.build());


                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putString("TEXT", ID);
                                    editor.apply();
                                }
                            }

                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "End element " + nodeName);
                        if("marker".equalsIgnoreCase(nodeName))
                        {

                        }
                    }
                }





                //POBIERANIE OBRYSÓW DZIAŁEK
                if (!TextUtils.isEmpty(nodeName)) {
                    if (eventType == XmlPullParser.START_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "Start element " + nodeName);

                        if ("markerd".equalsIgnoreCase(nodeName)){
                            ile_znacznikow = Integer.parseInt(xmlPullParser.getAttributeValue(null, "ile_znacznikow"));
                            ile_znacz.add(ile_znacznikow);
                            String ID = xmlPullParser.getAttributeValue(null, "id_dzialki");
                            nazwax1.add(xmlPullParser.getAttributeValue(null, "nazwa_w"));
                            uprawa.add(xmlPullParser.getAttributeValue(null, "uprawa"));
                            for (int i=0;i<ile_znacznikow;i++)
                            {
                                lat.add(Double.parseDouble(xmlPullParser.getAttributeValue(null, "lat"+i)));
                                lng.add(Double.parseDouble(xmlPullParser.getAttributeValue(null, "lng"+i)));
                            }
                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "End element " + nodeName);


                        if("markerd".equalsIgnoreCase(nodeName))
                        {

                        }
                    }
                }

                //POBIERANIE CEN
                if (!TextUtils.isEmpty(nodeName)) {
                    if (eventType == XmlPullParser.START_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "Start element " + nodeName);

                        if ("info".equalsIgnoreCase(nodeName)){
                            nazwac.add(xmlPullParser.getAttributeValue(null, "nazwa"));
                            cena.add(xmlPullParser.getAttributeValue(null, "cena"));
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "End element " + nodeName);
                        if("info".equalsIgnoreCase(nodeName))
                        {

                        }
                    }
                }


                //POBIERANIE DANYCH GOSPODARSTWA
                if (!TextUtils.isEmpty(nodeName)) {
                    if (eventType == XmlPullParser.START_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "Start element " + nodeName);

                        if ("gospodarstwo".equalsIgnoreCase(nodeName)){
                            email=xmlPullParser.getAttributeValue(null, "email");
                            nick=xmlPullParser.getAttributeValue(null, "nick");
                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "End element " + nodeName);


                        if("gospodarstwo".equalsIgnoreCase(nodeName))
                        {

                        }
                    }
                }

                //POBIERANIE DANYCH DZIALEK
                if (!TextUtils.isEmpty(nodeName)) {
                    if (eventType == XmlPullParser.START_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "Start element " + nodeName);

                        if ("dzialka".equalsIgnoreCase(nodeName)){
                            id_d.add(xmlPullParser.getAttributeValue(null, "id"));
                            nr_e_d.add(xmlPullParser.getAttributeValue(null, "nr_e_d"));
                            nazwa_w.add(xmlPullParser.getAttributeValue(null, "nazwa_w"));
                            pow.add(xmlPullParser.getAttributeValue(null, "pow"));
                            adres.add(xmlPullParser.getAttributeValue(null, "adres"));
                            uprawa_d.add(xmlPullParser.getAttributeValue(null, "uprawa"));
                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "End element " + nodeName);


                        if("dzialka".equalsIgnoreCase(nodeName))
                        {

                        }
                    }
                }


                //POBIERANIE ZABIEGOW OCHRONY ROSLIN
                if (!TextUtils.isEmpty(nodeName)) {
                    if (eventType == XmlPullParser.START_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "Start element " + nodeName);

                        if ("zabieg_o".equalsIgnoreCase(nodeName)){
                            id_z_o_r.add(xmlPullParser.getAttributeValue(null, "id"));
                            nazwa_w_d_z_o_r.add(xmlPullParser.getAttributeValue(null, "nazwa_w_d"));
                            uprawa_z_o_r.add(xmlPullParser.getAttributeValue(null, "uprawa"));
                            pow_z_o_r.add(xmlPullParser.getAttributeValue(null, "pow_z"));
                            data_z_o_r.add(xmlPullParser.getAttributeValue(null, "data"));
                            nazwa_s_z_o_r.add(xmlPullParser.getAttributeValue(null, "nazwa_s"));
                            dawka_z_o_r.add(xmlPullParser.getAttributeValue(null, "dawka"));
                            uwagi_z_o_r.add(xmlPullParser.getAttributeValue(null, "uwagi"));
                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "End element " + nodeName);


                        if("zabieg_o".equalsIgnoreCase(nodeName))
                        {

                        }
                    }
                }


                //POBIERANIE PRACOWNIKÓW
                if (!TextUtils.isEmpty(nodeName)) {
                    if (eventType == XmlPullParser.START_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "Start element " + nodeName);

                        if ("pracownik".equalsIgnoreCase(nodeName)){
                            id_p.add(xmlPullParser.getAttributeValue(null, "id"));
                            imie_p.add(xmlPullParser.getAttributeValue(null, "imie"));
                            nazwisko_p.add(xmlPullParser.getAttributeValue(null, "nazwisko"));
                            telefon_p.add(xmlPullParser.getAttributeValue(null, "telefon"));
                            placa_p.add(xmlPullParser.getAttributeValue(null, "placa"));

                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "End element " + nodeName);


                        if("pracownik".equalsIgnoreCase(nodeName))
                        {

                        }
                    }
                }



                //POBIERANIE BYDŁA
                if (!TextUtils.isEmpty(nodeName)) {
                    if (eventType == XmlPullParser.START_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "Start element " + nodeName);

                        if ("sztuka".equalsIgnoreCase(nodeName)){
                            id_b.add(xmlPullParser.getAttributeValue(null, "id"));
                            nr_zwierzecia.add(xmlPullParser.getAttributeValue(null, "nr_zwierzecia"));
                            data_urodzenia.add(xmlPullParser.getAttributeValue(null, "data_urodzenia"));
                            plec.add(xmlPullParser.getAttributeValue(null, "plec"));
                            kod_rasy.add(xmlPullParser.getAttributeValue(null, "kod_rasy"));
                            data_oznakowania.add(xmlPullParser.getAttributeValue(null, "data_oznakowania"));
                            nr_matki.add(xmlPullParser.getAttributeValue(null, "nr_matki"));
                            nr_ojca.add(xmlPullParser.getAttributeValue(null, "nr_ojca"));
                            data_przybycia.add(xmlPullParser.getAttributeValue(null, "data_przybycia"));
                            kod_zdarzenia_p.add(xmlPullParser.getAttributeValue(null, "kod_zdarzenia_p"));
                            dane_przybycia.add(xmlPullParser.getAttributeValue(null, "dane_przybycia"));
                            data_ubycia.add(xmlPullParser.getAttributeValue(null, "data_ubycia"));
                            kod_zdarzenia_u.add(xmlPullParser.getAttributeValue(null, "kod_zdarzenia_u"));
                            dane_ubycia.add(xmlPullParser.getAttributeValue(null, "dane_ubycia"));
                            dane_przewoznika.add(xmlPullParser.getAttributeValue(null, "dane_przewoznika"));
                            uwagi.add(xmlPullParser.getAttributeValue(null, "uwagi"));

                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        Log.d(TAG_XML_PULL_PARSER, "End element " + nodeName);


                        if("sztuka".equalsIgnoreCase(nodeName))
                        {

                        }
                    }
                }



                eventType = xmlPullParser.next();
            }
        }catch(XmlPullParserException ex)
        {

        }finally {


            String retBuf1="170";
            return retBuf1;
        }
    }

    // Initialize ui controls.
    private void initControls()
    {
        if(parseXmlUsePullButton == null)
        {
         //   parseXmlUsePullButton = (Button)findViewById(R.id.xml_pull_parser_parse_button);
        }

        if(showXmlParseResultTextView == null)
        {
         //   showXmlParseResultTextView = (TextView)findViewById(R.id.xml_parse_result_text_view);
        }

        if(showParseResultHandler == null)
        {
            // This handler waiting for message from activity child thread.
            showParseResultHandler = new Handler()
            {
                @Override
                public void handleMessage(Message msg) {
                    // If the message want to display xml parse result.
                    if(msg.what == MESSAGE_SHOW_XML_PARSE_RESULT)
                    {
                        // Get message bundle data.
                        Bundle bundle = msg.getData();

                        // Get xml parse result.
                        String xmlParseResult = bundle.getString(KEY_XML_PARSE_RESULT);

                        // Show the result in text view.
                        showXmlParseResultTextView.setText(xmlParseResult);
                    }
                }
            };
        }

        if(okHttpClient == null)
        {
            okHttpClient = new OkHttpClient();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.glowna, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;



    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    LAT=location.getLatitude();
                                    LON=location.getLongitude();
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }


    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            LAT=mLastLocation.getLatitude();
            LON=mLastLocation.getLongitude();
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }

    public void onClickpogoda(View v){
        Uri uri= Uri.parse("https://myfarm.pl/mobilna/pogoda.php?lat="+LAT+"&lng="+LON+"&id="+x);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }



}
