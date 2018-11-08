package com.example.android.testappetsy.activity.about.mvp;

import android.content.Intent;

import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;

public interface AboutContract {

    interface Model {
        interface OnGettingData {
            void returnData(Product product, Image image, boolean fromSaved);
        }

        void getData(Intent intent, OnGettingData onGettingData);
    }

    interface View {
        void showData(Product product, Image image, boolean fromSaved);
    }

    interface Presenter {
        void getDataFromIntent(Intent intent);

        void onDestroy();
    }
}

