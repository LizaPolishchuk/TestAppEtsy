package com.example.android.testappetsy.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultProduct {
    @SerializedName("results")
    private List<Product> resultsProduct;

    public List<Product> getResultsProduct() {
        return resultsProduct;
    }

    public void setResultsProduct(List<Product> resultsProduct) {
        this.resultsProduct = resultsProduct;
    }
}
