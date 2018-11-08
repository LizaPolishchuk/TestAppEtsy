package com.example.android.testappetsy.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.testappetsy.R;
import com.example.android.testappetsy.data.Image;
import com.example.android.testappetsy.data.Product;
import com.example.android.testappetsy.database.MyDatabase;
import com.example.android.testappetsy.utils.IntentAbout;
import com.example.android.testappetsy.utils.SavedRepository;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSaved extends RecyclerView.Adapter<AdapterSaved.ViewHolder> {

    private List<Product> productList;
    private List<Image> imageList;
    private MyDatabase database;

    public AdapterSaved(List<Product> productList, List<Image> imageList, MyDatabase database) {
        this.productList = productList;
        this.imageList = imageList;
        this.database = database;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Product product = productList.get(position);
        final Image image = imageList.get(position);
        holder.textView.setText(product.getTitle());
        Picasso.get().load(image.getUrlSmallImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentAbout.startActivity(holder.imageView.getContext(), product, image, true);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog deleteDialog = new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle(R.string.dialog_remove_title)
                        .setMessage(R.string.dialog_remove_message)
                        .setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                productList.remove(holder.getAdapterPosition());
                                imageList.remove(holder.getAdapterPosition());
                                SavedRepository.removeData(database, product, image);
                                notifyItemRemoved(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton(R.string.btn_no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).create();
                deleteDialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_item)
        ImageView imageView;
        @BindView(R.id.tv_title_item)
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

