package com.example.android.testappetsy.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.testappetsy.data.Image;

import java.util.List;

@Dao
public interface DaoImage {

    @Insert
    void putToDb(List<Image> imageList);

    @Delete
    void deleteFromDb(Image image);

    @Delete
    void deleteAllFromDb(List<Image> imageList);

    @Query("SELECT*FROM image")
    List<Image> getImages();
}
