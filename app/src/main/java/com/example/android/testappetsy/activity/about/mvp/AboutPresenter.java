package com.example.android.testappetsy.activity.about.mvp;

import android.content.Intent;

import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;

public class AboutPresenter implements AboutContract.Presenter, AboutContract.Model.OnGettingData {

    private AboutContract.Model model;
    private AboutContract.View view;

    AboutPresenter(AboutContract.View view) {
        this.view = view;
        model = new AboutModel();
    }

    @Override
    public void getDataFromIntent(Intent intent) {
        model.getData(intent, this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void returnData(Product product, Image image, boolean fromSaved) {
        if (view != null) {
            view.showData(product, image, fromSaved);
        }
    }
}

