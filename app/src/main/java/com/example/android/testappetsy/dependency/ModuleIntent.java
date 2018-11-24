package com.example.android.testappetsy.dependency;

import android.content.Intent;

import dagger.Module;
import dagger.Provides;

/**
 * Module to inject intent
 */
@Module
public class ModuleIntent {

    private Intent intent;

    public ModuleIntent(Intent intent) {
        this.intent = intent;
    }

    @Provides
    public Intent getIntent(){
        return intent;
    }
}
