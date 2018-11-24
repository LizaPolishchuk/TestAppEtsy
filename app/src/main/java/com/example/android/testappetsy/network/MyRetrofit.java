package com.example.android.testappetsy.network;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit{

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://openapi.etsy.com/v2/")
            .build();

    @Inject
    public MyRetrofit(){
    }

    public ApiData getApiData() {
        return retrofit.create(ApiData.class);
    }
}