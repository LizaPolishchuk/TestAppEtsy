package com.example.android.testappetsy.dependency;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.android.testappetsy.database.DatabaseHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Module to inject database
 */
@Module(includes = ModuleContext.class)
public class ModuleDatabase {

    @SingleScope
    @Provides
    public DatabaseHelper getDatabase(Context context){
        String DB_NAME = "dbSaved";
        return Room.databaseBuilder(context, DatabaseHelper.class, DB_NAME)
                .build();
    }
}
