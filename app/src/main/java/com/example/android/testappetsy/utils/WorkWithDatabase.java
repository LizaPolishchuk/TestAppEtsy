package com.example.android.testappetsy.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;
import com.example.android.testappetsy.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

/**
 * Class for working with Room not in main thread using executors and asyncTasks
 */
public class WorkWithDatabase {

    public static DatabaseHelper database;

    @Inject
    public WorkWithDatabase(DatabaseHelper database){
        WorkWithDatabase.database = database;
        Log.d("myLogs", "WorkWithDb: " + database.toString());
    }

    public static List<Product> getProductList() {
        AsyncTask<Void, Void, List<Product>> asyncTask = new AsyncTask<Void, Void, List<Product>>() {
            @Override
            protected List<Product> doInBackground(Void... voids) {
                Log.d("myLogs", "getPrList: " + database.getDaoProduct().getProducts().size());
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

    public static List<Image> getImageList() {
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

    public void putToDb(final List<Product> productList, final List<Image> imageList) {
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

    public void deleteFromDb(final Product product, final Image image) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.getDaoProduct().deleteFromDb(product);
                database.getDaoImage().deleteFromDb(image);
            }
        });
    }

    public void clearDb() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.getDaoProduct().deleteAllFromDb(getProductList());
                database.getDaoImage().deleteAllFromDb(getImageList());
            }
        });
    }
}
