package com.example.android.testappetsy.fragments.search.mvp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.testappetsy.R;
import com.example.android.testappetsy.activity.results.pagination.ResultActivity;
import com.example.android.testappetsy.network.MyRetrofit;
import com.example.android.testappetsy.utils.CheckingConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**The fragment in which the search is performed*/
public class FragmentSearch extends Fragment implements SearchContract.View, View.OnClickListener {

    @BindView(R.id.spinner_category)
    Spinner spinnerCategory;
    @BindView(R.id.et_keywords)
    EditText etKeywords;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    SearchPresenter presenter;
    Unbinder unbinder;
    MyRetrofit retrofit;
    Context context;
    String[] categoryNames;

    public static final String STRING_CATEGORY = "category";
    public static final String STRING_KEYWORDS = "keywords";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit = new MyRetrofit();
        presenter = new SearchPresenter(this);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        btnSubmit.setOnClickListener(this);
        presenter.getCategories(retrofit);
        return view;
    }

    /**Obtaining category names and display them in a spinner*/
    @Override
    public void showResults(String[] categoryNames, String[] shortNames) {
        this.categoryNames = categoryNames;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.tv_item_spinner, shortNames);
        spinnerCategory.setAdapter(adapter);
    }

    /**If there is no Internet connection will show the alert dialog with this warning */
    @Override
    public void onClick(View view) {
        if (!CheckingConnection.hasConnected(view.getContext()) || categoryNames == null) {
            AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                    .setTitle(R.string.dialog_warning_title)
                    .setMessage(R.string.dialog_warning_message)
                    .setNeutralButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            presenter.getCategories(retrofit);
                            dialogInterface.cancel();
                        }
                    })
                    .create();
            dialog.show();
        } else {
            String keywords = etKeywords.getText().toString();
            if (keywords.isEmpty()) {
                Toast.makeText(context, R.string.add_keywords, Toast.LENGTH_SHORT).show();
            } else {
                int position = spinnerCategory.getSelectedItemPosition();
                String category = categoryNames[position];

                Intent intent = new Intent(getContext(), ResultActivity.class);
                intent.putExtra(STRING_CATEGORY, category);
                intent.putExtra(STRING_KEYWORDS, keywords);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}

