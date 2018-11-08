package com.example.android.testappetsy.adapters;

import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.testappetsy.R;
import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;
import com.example.android.testappetsy.data.Results;
import com.example.android.testappetsy.utils.IntentAbout;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaginationAdapter extends PagedListAdapter<Results, PaginationAdapter.MyViewHolder> {

    public PaginationAdapter(@NonNull DiffUtil.ItemCallback<Results> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_item)
        ImageView imageView;
        @BindView(R.id.tv_title_item)
        TextView textView;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindTo(final Results results) {

            if (getAdapterPosition() < results.getProductList().size()) {
                final Product product = results.getProductList().get(getAdapterPosition());
                List<Image> imageList = results.getImageList().get(getAdapterPosition());
                final Image image = imageList.get(getAdapterPosition());

                textView.setText(product.getTitle());
                Picasso.get().load(image.getUrlSmallImage()).into(imageView);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IntentAbout.startActivity(imageView.getContext(), product, image, false);
                    }
                });
            } else {
                itemView.setVisibility(View.GONE);
            }
        }
    }
}
