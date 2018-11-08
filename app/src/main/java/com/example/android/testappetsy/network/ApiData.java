package com.example.android.testappetsy.network;

import com.example.android.testappetsy.data.ResultCategory;
import com.example.android.testappetsy.data.ResultImage;
import com.example.android.testappetsy.data.ResultProduct;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiData {

    String API_KEY = "s5qio4s53l4t6l77b523e437";

    @GET("taxonomy/categories?api_key=" + API_KEY)
    Call<ResultCategory> getCategories();

    @GET("listings/active?api_key=" + API_KEY)
    Call<ResultProduct> getProducts(@Query("limit") int limit, @Query("offset") int offset, @Query("category") String category, @Query("keywords") String keywords);

    @GET("listings/{listing_id}/images?api_key=" + API_KEY)
    Call<ResultImage> getImages(@Path("listing_id") int listingId);
}
