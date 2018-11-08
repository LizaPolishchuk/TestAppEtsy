package com.example.android.testappetsy.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.testappetsy.data.Product;

import java.util.List;

@Dao
public interface DaoProduct {

    @Insert
    void putToDb(List<Product> productList);

    @Delete
    void deleteFromDb(Product product);

    @Delete
    void deleteAllFromDb(List<Product> productList);

    @Query("SELECT*FROM product")
    List<Product> getProducts();
}
