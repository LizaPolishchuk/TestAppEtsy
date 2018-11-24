package com.example.android.testappetsy.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.android.testappetsy.R;
import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;
import com.example.android.testappetsy.fragments.FragmentSaved;

import java.util.ArrayList;
import java.util.List;

/**When the product is added to the savedList, it is checked that there is no identical products */
public class SavedRepository {

    private static List<Product> productList = new ArrayList<>();

    public static void putData(Context context, Product product, Image image) {

        productList.addAll(WorkWithDatabase.getProductList());

        for (Product prod : productList) {
            if (prod.getTitle().equals(product.getTitle())) {
                Toast.makeText(context, R.string.toast_already_saved, Toast.LENGTH_SHORT).show();
                productList.clear();
                return;
            }
        }
        FragmentSaved.onUpdate(product, image);
        productList.clear();
    }
}
