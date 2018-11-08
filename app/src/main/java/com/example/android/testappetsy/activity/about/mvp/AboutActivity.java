package com.example.android.testappetsy.activity.about.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.testappetsy.R;
import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;
import com.example.android.testappetsy.database.MyDatabase;
import com.example.android.testappetsy.utils.SavedRepository;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements AboutContract.View {

    @BindView(R.id.tv_title_about)
    TextView tvTitle;
    @BindView(R.id.tv_description_about)
    TextView tvDescription;
    @BindView(R.id.tv_price_about)
    TextView tvPrice;
    @BindView(R.id.tv_currency_code_about)
    TextView tvCurrencyCode;
    @BindView(R.id.image_about)
    ImageView imageView;
    @BindView(R.id.btn_save_about)
    Button btnSave;

    AboutPresenter presenter;
    MyDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        database = new MyDatabase(this);

        presenter = new AboutPresenter(this);
        presenter.getDataFromIntent(getIntent());
    }

    @Override
    public void showData(final Product product, final Image image, boolean fromSaved) {
        tvTitle.setText(product.getTitle());
        tvDescription.setText(product.getDescription());
        tvPrice.setText(product.getPrice());
        tvCurrencyCode.setText(product.getCurrencyCode());
        Picasso.get().load(image.getUrlBigImage()).into(imageView);

        if (fromSaved) {
            btnSave.setVisibility(View.GONE);
        } else {
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SavedRepository.putData(view.getContext(), database, product, image);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
