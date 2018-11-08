package com.example.android.testappetsy.activity.results.pagination;

import android.arch.paging.DataSource;
import android.content.Context;
import android.content.Intent;

import com.example.android.testappetsy.data.Results;
import com.example.android.testappetsy.network.MyRetrofit;

public class DataFactory extends DataSource.Factory<Integer, Results> {

    private MyRetrofit retrofit;
    private Intent intent;
    private Context context;
    private OnGettingResults.OnShowInView onShowInView;

    DataFactory(Context context, MyRetrofit retrofit, Intent intent, OnGettingResults.OnShowInView onShowInView) {
        this.context = context;
        this.retrofit = retrofit;
        this.intent = intent;
        this.onShowInView = onShowInView;
    }

    @Override
    public android.arch.paging.DataSource<Integer, Results> create() {
        return new ResultsSource(context, retrofit, intent, onShowInView);
    }
}
