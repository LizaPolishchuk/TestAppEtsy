package com.example.android.testappetsy.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit{
    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://openapi.etsy.com/v2/")
            .build();

    private ApiData apiData = retrofit.create(ApiData.class);

    public ApiData getApiData() {
        return apiData;
    }
}