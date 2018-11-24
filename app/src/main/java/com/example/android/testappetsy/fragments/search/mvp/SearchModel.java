package com.example.android.testappetsy.fragments.search.mvp;

import android.support.annotation.NonNull;

import com.example.android.testappetsy.data.Category;
import com.example.android.testappetsy.data.ResultCategory;
import com.example.android.testappetsy.network.MyRetrofit;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**Obtaining category names from Etsy API*/
public class SearchModel implements SearchContract.Model {

    private List<Category> categoryList = new ArrayList<>();
    private String[] categoryNames;
    private String[] shortNames;

    private MyRetrofit retrofit;

    @Inject
    public SearchModel(MyRetrofit retrofit){
        this.retrofit = retrofit;
    }
    @Override
    public void getData(final OnGettingData onGettingData) {
        retrofit.getApiData().getCategories().enqueue(new Callback<ResultCategory>() {
            @Override
            public void onResponse(@NonNull Call<ResultCategory> call, @NonNull Response<ResultCategory> response) {
                if (response.body() != null) {
                    categoryList.addAll(response.body().getResultsCategory());
                }

                categoryNames = new String[categoryList.size()];
                shortNames = new String[categoryList.size()];

                for (int i = 0; i < categoryList.size(); i++) {
                    Category category = categoryList.get(i);
                    categoryNames[i] = category.getCategoryName();
                    shortNames[i] = category.getShortName();
                }
                onGettingData.onFinished(categoryNames, shortNames);
            }

            @Override
            public void onFailure(@NonNull Call<ResultCategory> call, @NonNull Throwable t) {
                onGettingData.onFailure(t);
            }
        });
    }
}
