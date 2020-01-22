package com.example.android.tourguide;

import java.io.Serializable;

public class DataSetter implements Serializable {
    private int details;
    private String titles;
    private int[] images;
    private int longitude;
    private int latitude;
    private boolean Isinit=false;

    public int getDetails(){return details;}

    public String getTitle(){return titles;}

    public int[] getImages(){return images;}

    public int getLongitude(){return longitude;}

    public int getLatitude(){return latitude;}

    public DataSetter(int detail,String title,int[] image ,int lng,int lat){
        details=detail;
        titles=title;
        images=image;
        longitude=lng;
        latitude=lat;
    }
}
