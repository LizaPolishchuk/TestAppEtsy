package com.example.android.testappetsy.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class MyDatabase {

    private DaoProduct daoProduct;
    private DaoImage daoImage;
    private final String DB_NAME = "dbSaved";


    public MyDatabase(Context context) {
        DatabaseHelper database = Room.databaseBuilder(context, DatabaseHelper.class, DB_NAME)
                .build();
        daoProduct = database.getDaoProduct();
        daoImage = database.getDaoImage();
    }

    public DaoImage getDaoImage() {
        return daoImage;
    }

    public DaoProduct getDaoProduct() {
        return daoProduct;
    }
}
