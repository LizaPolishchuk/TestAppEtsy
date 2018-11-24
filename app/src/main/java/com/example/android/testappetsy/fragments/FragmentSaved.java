package com.example.android.testappetsy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.testappetsy.R;
import com.example.android.testappetsy.adapters.AdapterSaved;
import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;
import com.example.android.testappetsy.dependency.DaggerSavedComponent;
import com.example.android.testappetsy.dependency.ModuleContext;
import com.example.android.testappetsy.dependency.SavedComponent;
import com.example.android.testappetsy.utils.WorkWithDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**The fragment that display a list of saved products*/
public class FragmentSaved extends Fragment {

    @BindView(R.id.recycler_view_saved)
    RecyclerView recyclerView;

    static List<Product> productList = new ArrayList<>();
    static List<Image> imageList = new ArrayList<>();

    Unbinder unbinder;
    static AdapterSaved adapter;
    static WorkWithDatabase database;

    /**This method is called when a new item is added to the database*/
    public static void onUpdate(Product product, Image image) {
        if (productList.size() != 0 && imageList.size() != 0) {
            database.clearDb();
        }
        productList.add(product);
        imageList.add(image);

        database.putToDb(productList, imageList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        productList.clear();
        imageList.clear();
        productList.addAll(WorkWithDatabase.getProductList());
        imageList.addAll(WorkWithDatabase.getImageList());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SavedComponent component = DaggerSavedComponent.builder()
                .moduleContext(new ModuleContext(getContext()))
                .build();
        database = component.getDatabase();

        adapter = new AdapterSaved(productList, imageList, database);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
