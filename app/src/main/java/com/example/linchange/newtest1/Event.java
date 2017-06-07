package com.example.linchange.newtest1;

/**
 * Created by Lin Change on 2016-08-15.
 */
public class Event {
    public int imgName;
    public String date;
    public String place;

    public Event(int imgName, String place, String date) {
        this.imgName = imgName;
        this.place = place;
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImg(int imgName) {
        this.imgName = imgName;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public int getImgName() {
        return imgName;
    }

    public String getPlace() {
        return place;
    }
}
