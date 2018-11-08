package com.example.android.testappetsy.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.android.testappetsy.R;
import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;
import com.example.android.testappetsy.database.MyDatabase;
import com.example.android.testappetsy.fragments.FragmentSaved;

import java.util.ArrayList;
import java.util.List;

public class SavedRepository {

    private static List<Product> productList = new ArrayList<>();

    public static void putData(Context context, MyDatabase database, Product product, Image image) {

        productList.addAll(WorkWithDatabase.getProductList(database));

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

    public static void removeData(MyDatabase database, Product product, Image image) {
        WorkWithDatabase.deleteFromDb(database, product, image);
    }
}
