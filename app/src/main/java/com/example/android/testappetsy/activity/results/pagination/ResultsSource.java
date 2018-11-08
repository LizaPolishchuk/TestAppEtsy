package com.example.android.testappetsy.activity.results.pagination;

import android.arch.paging.PositionalDataSource;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;
import com.example.android.testappetsy.data.ResultImage;
import com.example.android.testappetsy.data.ResultProduct;
import com.example.android.testappetsy.data.Results;
import com.example.android.testappetsy.fragments.search.mvp.FragmentSearch;
import com.example.android.testappetsy.network.MyRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsSource extends PositionalDataSource<Results> implements OnGettingResults {

    private MyRetrofit retrofit;
    private Context context;
    private String category;
    private String keywords;
    private OnShowInView onShowInView;

    private List<Results> resultsList = new ArrayList<>();
    private List<Product> productList = new ArrayList<>();
    private List<Image> imageList = new ArrayList<>();
    private List<List<Image>> imageResults = new ArrayList<>();
    private int[] arrListingId;
    private int num = 0;

    private PositionalDataSource.LoadInitialCallback<Results> initialCallback;
    private LoadRangeCallback<Results> rangeCallback;

    ResultsSource(Context context, MyRetrofit retrofit, Intent intent, OnShowInView onShowInView) {
        this.context = context;
        this.retrofit = retrofit;
        this.onShowInView = onShowInView;
        category = intent.getStringExtra(FragmentSearch.STRING_CATEGORY);
        keywords = intent.getStringExtra(FragmentSearch.STRING_KEYWORDS);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Results> callback) {
        getData(params.requestedLoadSize, params.requestedStartPosition, category, keywords, callback, null, this);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Results> callback) {
        if (params.startPosition<=productList.size()) {
            getData(params.loadSize, productList.size(), category, keywords, null, callback, this);
        }
    }

    private void getData(int limit, final int offset, String category, String keywords, final LoadInitialCallback<Results> callback, final LoadRangeCallback<Results> callbackRange, final OnGettingResults onGettingResults) {
        retrofit.getApiData().getProducts(limit, offset, category, keywords).enqueue(new Callback<ResultProduct>() {
            @Override
            public void onResponse(@NonNull Call<ResultProduct> call, @NonNull Response<ResultProduct> response) {

                if (response.body() != null) {
                    productList.addAll(response.body().getResultsProduct());
                }

                initialCallback = callback;
                rangeCallback = callbackRange;

                arrListingId = new int[productList.size()];
                for (int i = 0; i < productList.size(); i++) {
                    Product product = productList.get(i);
                    arrListingId[i] = product.getListingId();
                }
                onGettingResults.onGetProduct(productList, arrListingId);
            }

            @Override
            public void onFailure(@NonNull Call<ResultProduct> call, @NonNull Throwable t) {
                onGettingResults.onFailure(t);
            }
        });
    }

    private void getImage(final OnGettingResults onGettingResults, int listingId) {
        retrofit.getApiData().getImages(listingId).enqueue(new Callback<ResultImage>() {
            @Override
            public void onResponse(@NonNull Call<ResultImage> call, @NonNull Response<ResultImage> response) {

                if (response.body() != null) {
                    imageList.addAll(response.body().getResultsImage());
                }
                if (productList.size() != imageResults.size()) {
                    imageResults.add(imageList);
                }
                onGettingResults.onGetImage(imageResults);
            }

            @Override
            public void onFailure(@NonNull Call<ResultImage> call, @NonNull Throwable t) {
                onGettingResults.onFailure(t);
            }
        });

    }

    @Override
    public void onGetProduct(List<Product> productList, int[] arrListingId) {
        if (productList.size() == 0) {
            onShowInView.nothingFind();
        } else {
            getImage(this, arrListingId[0]);
        }
    }

    @Override
    public void onGetImage(List<List<Image>> imageResults) {
        if (imageResults.size() != productList.size()) {
            num++;
            getImage(this, arrListingId[num]);
        } else {
            Results results = new Results(productList, imageResults);
            resultsList.add(results);

            if (initialCallback != null) {
                initialCallback.onResult(resultsList, 0);
                onShowInView.hideProgress();
            } else {
                rangeCallback.onResult(resultsList);
            }
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        Toast.makeText(context, "Sorry! Something going wrong :(", Toast.LENGTH_SHORT).show();
        Log.d(context.getPackageName(), "onFailure: " + throwable);
    }
}

