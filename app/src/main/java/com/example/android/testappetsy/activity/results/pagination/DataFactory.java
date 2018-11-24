package com.example.android.testappetsy.activity.results.pagination;

import android.arch.paging.DataSource;

import com.example.android.testappetsy.data.Results;
import com.example.android.testappetsy.dependency.ResultsComponent;

import javax.inject.Inject;

public class DataFactory extends DataSource.Factory<Integer, Results> {

    private ResultsComponent component;

    @Inject
    public DataFactory(ResultsComponent component){
        this.component = component;
    }

    @Override
    public android.arch.paging.DataSource<Integer, Results> create() {
        return component.getSource();
    }
}
