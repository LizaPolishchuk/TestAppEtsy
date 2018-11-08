package com.example.android.testappetsy.fragments.search.mvp;

import com.example.android.testappetsy.network.MyRetrofit;

public interface SearchContract {

    interface Model {
        interface OnGettingData {
            void onFinished(String[] categoryNames, String[] shortNames);

            void onFailure(Throwable throwable);
        }

        void getData(OnGettingData onGettingData, MyRetrofit retrofit);
    }

    interface View {
        void showResults(String[] categoryNames, String[] shortNames);
    }

    interface Presenter {
        void getCategories(MyRetrofit retrofit);

        void onDestroy();
    }
}
