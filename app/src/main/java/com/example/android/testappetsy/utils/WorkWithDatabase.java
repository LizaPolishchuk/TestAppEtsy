package com.example.android.testappetsy.utils;

import android.os.AsyncTask;

import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;
import com.example.android.testappetsy.database.MyDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WorkWithDatabase {

    public static void putToDb(final MyDatabase database, final List<Product> productList, final List<Image> imageList) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.getDaoProduct().deleteAllFromDb(database.getDaoProduct().getProducts());
                database.getDaoImage().deleteAllFromDb(database.getDaoImage().getImages());

                database.getDaoProduct().putToDb(productList);
                database.getDaoImage().putToDb(imageList);
            }
        });
    }

    public static List<Product> getProductList(final MyDatabase database) {
        AsyncTask<Void, Void, List<Product>> asyncTask = new AsyncTask<Void, Void, List<Product>>() {
            @Override
            protected List<Product> doInBackground(Void... voids) {
                return database.getDaoProduct().getProducts();
            }
        };
        asyncTask.execute();
        try {
            return asyncTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Image> getImageList(final MyDatabase database) {
        AsyncTask<Void, Void, List<Image>> asyncTask = new AsyncTask<Void, Void, List<Image>>() {
            @Override
            protected List<Image> doInBackground(Void... voids) {
                return database.getDaoImage().getImages();
            }
        };
        asyncTask.execute();
        try {
            return asyncTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void deleteFromDb(final MyDatabase database, final Product product, final Image image) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.getDaoProduct().deleteFromDb(product);
                database.getDaoImage().deleteFromDb(image);
            }
        });
    }

    public static void clearDb(final MyDatabase database) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.getDaoProduct().deleteAllFromDb(getProductList(database));
                database.getDaoImage().deleteAllFromDb(getImageList(database));
            }
        });
    }
}
