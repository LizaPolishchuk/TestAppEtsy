package com.example.android.testappetsy.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultCategory {
    @SerializedName("results")
    private List<Category> resultsCategory;

    public List<Category> getResultsCategory() {
        return resultsCategory;
    }

    public void setResultsCategory(List<Category> resultsCategory) {
        this.resultsCategory = resultsCategory;
    }
}
