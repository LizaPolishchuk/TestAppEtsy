package com.example.android.testappetsy.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Image implements Serializable {
    @NonNull
    @PrimaryKey
    @SerializedName("url_75x75")
    private String urlSmallImage;
    @SerializedName("url_170x135")
    private String urlBigImage;

    @NonNull
    public String getUrlSmallImage() {
        return urlSmallImage;
    }

    public void setUrlSmallImage(@NonNull String urlSmallImage) {
        this.urlSmallImage = urlSmallImage;
    }

    public String getUrlBigImage() {
        return urlBigImage;
    }

    public void setUrlBigImage(String urlBigImage) {
        this.urlBigImage = urlBigImage;
    }
}
