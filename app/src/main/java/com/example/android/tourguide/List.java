package com.example.android.tourguide;

public class List {
    private String Title;
    private int ImageId=NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED=-1;
    private int ColorId;

    public int getImageId(){return ImageId;}

    public String getTitle(){return Title;}

    public int getColorId(){return ColorId;}

    public List(String title,int imageId,int colorId){
        Title=title;
        ImageId=imageId;
        ColorId=colorId;
    }

    public boolean hasImage(){
        return ImageId!=NO_IMAGE_PROVIDED;
    }
}
