package com.example.android.testappetsy.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;

@Database(entities = {Product.class, Image.class}, version = 1, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract DaoProduct getDaoProduct();

    public abstract DaoImage getDaoImage();
}
