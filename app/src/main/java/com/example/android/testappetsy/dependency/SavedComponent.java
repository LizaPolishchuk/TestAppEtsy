package com.example.android.testappetsy.dependency;

import com.example.android.testappetsy.utils.WorkWithDatabase;

import dagger.Component;

/**
 * Component define from which modules dependencies are provided
 */
@SingleScope
@Component(modules = ModuleDatabase.class)
public interface SavedComponent {
    WorkWithDatabase getDatabase();
}
