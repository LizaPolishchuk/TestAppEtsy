package com.example.android.testappetsy.utils;

import android.content.Context;
import android.content.Intent;

import com.example.android.testappetsy.activity.about.mvp.AboutActivity;
import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;

public class IntentAbout {

    public static final String SERIALIZABLE_PRODUCT = "product";
    public static final String SERIALIZABLE_IMAGE = "image";
    public static final String BOOLEAN_SAVED = "saved";

    /**
     * Starting activity AboutActivity that describe the selected product
     */
    public static void startActivity(Context context, Product product, Image image, boolean fromSaved) {

        Intent intent = new Intent(context, AboutActivity.class);

        intent.putExtra(BOOLEAN_SAVED, fromSaved);
        intent.putExtra(SERIALIZABLE_PRODUCT, product);
        intent.putExtra(SERIALIZABLE_IMAGE, image);

        context.startActivity(intent);
    }

}
