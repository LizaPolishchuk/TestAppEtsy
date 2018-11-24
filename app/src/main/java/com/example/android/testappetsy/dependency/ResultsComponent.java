package com.example.android.testappetsy.dependency;

import com.example.android.testappetsy.activity.results.pagination.ResultsSource;

import dagger.Component;

/**
 * Component define from which modules dependencies are provided
 */
@SingleScope
@Component(modules = {ModuleContext.class, ModuleIntent.class, ModuleOnShow.class})
public interface ResultsComponent {
    ResultsSource getSource();
}
