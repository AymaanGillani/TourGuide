package com.example.android.tourguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    private String[] titles={"Charminar","Golconda Fort","Ramoji Film City","Hussain Sagar Lake","Birla Mandir,Planetarium & Museum","Chowmahalla Palace",
            "Qutub Shahi Tomb", "Nehru Zoological Park", "Salar Jung museum", "Falaknuma Palace", "KBR park"};
    private int[] details={R.string.details1,R.string.details2,R.string.details3,R.string.details4,R.string.details5,
            R.string.details6,/*R.string.details7,R.string.details8,R.string.details9,R.string.details10,R.string.details11*/};
    private int[][] images={{R.drawable.aa,R.drawable.ab, R.drawable.ac, R.drawable.ad,R.drawable.ae},
            {R.drawable.ba,R.drawable.bb,R.drawable.bc,R.drawable.bd,R.drawable.be,R.drawable.bf,R.drawable.bg},
            {R.drawable.ca,R.drawable.cb,R.drawable.cc,R.drawable.cd,R.drawable.ce},
            {R.drawable.da,R.drawable.db,R.drawable.dc,R.drawable.dd,R.drawable.de},
            {R.drawable.ea,R.drawable.eb,R.drawable.ec,R.drawable.ed,R.drawable.ee,R.drawable.ef,R.drawable.eg},
            {R.drawable.fa,R.drawable.fb,R.drawable.fc,R.drawable.fd,R.drawable.fe,R.drawable.ff,R.drawable.fg},
            {R.drawable.ga,R.drawable.gb,R.drawable.gc,R.drawable.gd,R.drawable.ge,R.drawable.gf},
            {R.drawable.ha,R.drawable.hb,R.drawable.hc,R.drawable.hd,R.drawable.he,R.drawable.hf,R.drawable.hg,R.drawable.hh,R.drawable.hi,R.drawable.hj},
            {R.drawable.ia,R.drawable.ib,R.drawable.ic,R.drawable.id,R.drawable.ie,R.drawable.ifi,R.drawable.ig,R.drawable.ih,R.drawable.ii,R.drawable.ij},
            {R.drawable.ja,R.drawable.jb,R.drawable.jc,R.drawable.jd,R.drawable.je,R.drawable.jf,R.drawable.jg,R.drawable.jh,R.drawable.ji,R.drawable.jj,R.drawable.jk,R.drawable.jl},
            {R.drawable.ka,R.drawable.kb,R.drawable.kc,R.drawable.kd,R.drawable.ke,R.drawable.kf,R.drawable.kg}};
    private int[] longitudes={R.string.lng1,R.string.lng2,R.string.lng3,R.string.lng4,R.string.lng5,R.string.lng6,R.string.lng7,R.string.lng8,
            R.string.lng9,R.string.lng10,R.string.lng11};
    private int[] latitudes={R.string.lat1,R.string.lat2,R.string.lat3,R.string.lat4,R.string.lat5,R.string.lat6,R.string.lat7,R.string.lat8,
            R.string.lat9,R.string.lat10,R.string.lat11};

    private int[] ImgResources={R.drawable.charminar,R.drawable.golconda,R.drawable.ramoji,R.drawable.hussainsagar,R.drawable.birla,R.drawable.chowmahalla,
            R.drawable.qutub,R.drawable.nehru,R.drawable.salar,R.drawable.falaknuma,R.drawable.kbr};
    private int[] ColorResources={R.color.colorLayout5,R.color.colorLayout2,R.color.colorLayout12,R.color.colorLayout1,R.color.colorLayout11,R.color.colorLayout4,
            R.color.colorLayout8,R.color.colorLayout13,R.color.colorLayout10,R.color.colorAccent,R.color.colorLayout3,R.color.colorLayout13};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        permissionRequest();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final ArrayList<List> items=new ArrayList<>();
        for (int i=0;i<titles.length;i++){
            items.add(new List(titles[i],ImgResources[i],ColorResources[i]));
        }
        ListAdapter adapter = new ListAdapter(this,items);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DataSetter data =new DataSetter(details[position],titles[position],images[position],longitudes[position],latitudes[position]);
                Intent layoutIntent = new Intent(MainActivity.this, Layout1Activity.class);
                layoutIntent.putExtra("actData",data);
                startActivity(layoutIntent);
            }
        });
    }

    private void permissionRequest() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    1);
        }
    }
}
