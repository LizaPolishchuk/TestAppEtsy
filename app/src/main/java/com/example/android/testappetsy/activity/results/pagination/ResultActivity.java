package com.example.android.testappetsy.activity.results.pagination;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.testappetsy.R;
import com.example.android.testappetsy.adapters.PaginationAdapter;
import com.example.android.testappetsy.data.Results;
import com.example.android.testappetsy.network.MyRetrofit;

import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity implements OnGettingResults.OnShowInView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view_results)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.tv_no_results)
    TextView tvNoResults;
    @BindView(R.id.progress_bar)
    ProgressBar progress;

    private PaginationAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        swipeLayout.setOnRefreshListener(this);

        MyRetrofit retrofit = new MyRetrofit();
        DataFactory dataFactory = new DataFactory(this, retrofit, getIntent(), this);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .setPrefetchDistance(15)
                .build();

        LiveData<PagedList<Results>> liveData = new LivePagedListBuilder<>(dataFactory, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build();

        adapter = new PaginationAdapter(new DiffUtil.ItemCallback<Results>() {
            @Override
            public boolean areItemsTheSame(Results oldItem, Results newItem) {
                return (oldItem.getProduct().getTitle().equals(newItem.getProduct().getTitle()));
            }

            @Override
            public boolean areContentsTheSame(Results oldItem, Results newItem) {
                return (oldItem.getProduct().getDescription().equals(newItem.getProduct().getDescription())
                        && oldItem.getProduct().getPrice().equals(newItem.getProduct().getPrice())
                        && oldItem.getImage().getUrlSmallImage().equals(newItem.getImage().getUrlSmallImage()));
            }
        });

        liveData.observe(this, new Observer<PagedList<Results>>() {
            @Override
            public void onChanged(@Nullable PagedList<Results> resultsData) {
                adapter.submitList(resultsData);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
        swipeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void nothingFind() {
        progress.setVisibility(View.GONE);
        tvNoResults.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        recreate();
    }
}
