package com.example.android.testappetsy.fragments.search.mvp;

import android.util.Log;

import com.example.android.testappetsy.dependency.DaggerSearchComponent;
import com.example.android.testappetsy.network.MyRetrofit;

public class SearchPresenter implements SearchContract.Presenter, SearchContract.Model.OnGettingData {

    private SearchContract.Model model;
    private SearchContract.View view;

    SearchPresenter(SearchContract.View view) {
        this.view = view;
        model = DaggerSearchComponent.create().getSearchModel();
    }

    @Override
    public void getCategories(MyRetrofit retrofit) {
        model.getData(this);
    }


    @Override
    public void onFinished(String[] categoryNames, String[] shortNames) {
        if (view != null) {
            view.showResults(categoryNames, shortNames);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.d("searchLogs", "onFailure: " + throwable);
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
