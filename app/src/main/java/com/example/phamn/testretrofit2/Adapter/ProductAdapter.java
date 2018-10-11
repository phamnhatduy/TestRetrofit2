package com.example.phamn.testretrofit2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phamn.testretrofit2.Model.Products;
import com.example.phamn.testretrofit2.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ShowHolder>{
    private List<Products> productList;
    private Context context;

    public ProductAdapter(List<Products> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ShowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);
        ShowHolder showHolder= new ShowHolder(view);
        return showHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowHolder showHolder, int i) {
        Products product = productList.get(i);
        showHolder.tvProductName.setText(product.getProductName());
        Glide.with(context).load(product.getThumnail())
                .override(150,150).centerCrop().into(showHolder.img_Thumnail);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ShowHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cardView) CardView cardView;
        @BindView(R.id.tv_Name) TextView tvProductName;
        @BindView(R.id.img_Thumnail) ImageView img_Thumnail;
        public ShowHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
