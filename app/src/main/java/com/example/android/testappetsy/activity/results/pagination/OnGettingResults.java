package com.example.android.testappetsy.activity.results.pagination;


import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;

import java.util.List;

public interface OnGettingResults {

    void onGetProduct(List<Product> productList, int[] arrListingId);

    void onGetImage(List<List<Image>> imageResults);

    void onFailure(Throwable throwable);

    interface OnShowInView {

        void hideProgress();

        void nothingFind();
    }
}

