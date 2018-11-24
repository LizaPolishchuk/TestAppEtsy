package com.example.android.testappetsy.activity.about.mvp;

import android.content.Intent;

import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;
import com.example.android.testappetsy.utils.IntentAbout;

/**Getting data from Intent*/
public class AboutModel implements AboutContract.Model {
    @Override
    public void getData(Intent intent, OnGettingData onGettingData) {

        Product product = (Product) intent.getSerializableExtra(IntentAbout.SERIALIZABLE_PRODUCT);
        Image image = (Image) intent.getSerializableExtra(IntentAbout.SERIALIZABLE_IMAGE);
        boolean fromSaved = intent.getBooleanExtra(IntentAbout.BOOLEAN_SAVED, false);

        onGettingData.returnData(product, image, fromSaved);
    }
}

