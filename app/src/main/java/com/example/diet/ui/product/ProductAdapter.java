package com.example.diet.ui.product;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diet.R;
import com.example.diet.cart.dto.Cart;
import com.example.diet.product.dto.Product;
import com.example.diet.ui.cart.CartActivity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    List<Product> products;
    Context context;
    Cart cart;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        Log.d("ProductName:",product.getProductName());
        holder.productName.setText(product.getProductName());
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.rating.setText(String.valueOf(product.getRate()));
        holder.purchaseNo.setText(String.format("%d lượt bán", product.getPurchase()));
        Glide.with(context)
                .load(product.getImages()[0])
                .into(holder.images);

        // Binding the event for details
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("productID", product.getProductId());
            Log.d("productID start", product.getProductId());
            context.startActivity(intent);
        });

        // Add to Cart button click listener
        holder.addToCart.setOnClickListener(v -> {
            Intent intent = new Intent(context, CartActivity.class);
            intent.putExtra("productID", product.getProductId());
            intent.putExtra("productPrice", product.getPrice());
            Log.d("productID Cart", product.getProductId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView productName;
        TextView price;
        TextView rating;
        TextView purchaseNo;
        ImageButton addToCart;

        public ViewHolder(View view) {
            super(view);
            images = view.findViewById(R.id.iv_product);
            productName = view.findViewById(R.id.tv_product_name);
            price = view.findViewById(R.id.tv_price);
            rating = view.findViewById(R.id.tv_rating);
            purchaseNo = view.findViewById(R.id.tv_purchase_no);
            addToCart = view.findViewById(R.id.addToCart);
        }
    }
}
