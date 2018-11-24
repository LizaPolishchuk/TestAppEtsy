package com.example.android.testappetsy.dependency;

import com.example.android.testappetsy.fragments.search.mvp.SearchModel;

import dagger.Component;

@Component
public interface SearchComponent {
    SearchModel getSearchModel();
}
