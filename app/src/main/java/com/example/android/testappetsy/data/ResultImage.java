package com.example.android.testappetsy.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultImage {
    @SerializedName("results")
    private List<Image> resultsImage;

    public List<Image> getResultsImage() {
        return resultsImage;
    }

    public void setResultsImage(List<Image> resultsImage) {
        this.resultsImage = resultsImage;
    }
}
