package com.example.android.testappetsy.dependency;

import com.example.android.testappetsy.activity.results.pagination.OnGettingResults;

import dagger.Module;
import dagger.Provides;

/**
 * Module to inject interface OnShowInView
 */
@Module
public class ModuleOnShow {
    private OnGettingResults.OnShowInView onShowInView;

    public ModuleOnShow(OnGettingResults.OnShowInView onShowInView) {
        this.onShowInView = onShowInView;
    }

    @Provides
    public OnGettingResults.OnShowInView getOnShowInView() {
        return onShowInView;
    }
}
