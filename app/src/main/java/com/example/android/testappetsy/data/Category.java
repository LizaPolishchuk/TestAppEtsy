package com.example.android.testappetsy.data;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("category_name")
    private String categoryName;
    @SerializedName("short_name")
    private String shortName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
