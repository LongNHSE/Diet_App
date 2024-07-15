package com.example.diet.ui.product;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diet.R;
import com.example.diet.product.dto.Product;

import java.util.List;

public class ProductAlternativeAdapter extends RecyclerView.Adapter<ProductAlternativeAdapter.ViewHolder> {

    List<Product> alternativeProducts;
    Context context;

    public ProductAlternativeAdapter(List<Product> alternativeProducts, Context context) {
        this.alternativeProducts = alternativeProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);


        ProductAlternativeAdapter.ViewHolder viewHolder = new ProductAlternativeAdapter.ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = alternativeProducts.get(position);
        Log.d("ProductName:",product.getProductName());
        holder.productName.setText(product.getProductName());
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.rating.setText(String.valueOf(product.getRate()));
        holder.purchaseNo.setText(String.format("%d lượt bán", product.getPurchase()));
        Glide.with(context)
                .load(product.getImages()[0])
                .into(holder.images);


        //Binding the event for details
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("productID", product.getProductId());
            Log.d("productID start", product.getProductId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return alternativeProducts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView productName;
        TextView price;
        TextView rating;
        TextView purchaseNo;


        public ViewHolder(View view) {
            super(view);
            images = (ImageView) view.findViewById(R.id.iv_product);
            productName = (TextView) view.findViewById(R.id.tv_product_name);
            price = (TextView) view.findViewById(R.id.tv_price);
            rating = (TextView) view.findViewById(R.id.tv_rating);
            purchaseNo = (TextView) view.findViewById(R.id.tv_purchase_no);
        }
    }
}
