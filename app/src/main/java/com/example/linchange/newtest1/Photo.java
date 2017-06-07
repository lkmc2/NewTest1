package com.example.linchange.newtest1;

import android.graphics.Bitmap;

/**
 * Created by Lin Change on 2016-08-16.
 */
public class Photo {
    private Bitmap bitmap;
    private String photoName;
    private String photoPath;

    public Photo(String photoName, String photoPath) {
//        this.bitmap = bitmap;
        this.photoName = photoName;
        this.photoPath = photoPath;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
