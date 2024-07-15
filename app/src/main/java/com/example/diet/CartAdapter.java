package com.example.diet;// CartAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.cart.dto.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItemList;
    private Context context;

    public CartAdapter(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);

        holder.itemName.setText(cartItem.getItemName());
        holder.itemPrice.setText(String.format("%,dđ", cartItem.getItemPrice())); // Format price as needed
        holder.itemQuantity.setText(String.valueOf(cartItem.getQuantity()));

        // Example: Increase and decrease button click listeners
        holder.increaseButton.setOnClickListener(v -> {
            // Handle increase quantity logic
        });

        holder.decreaseButton.setOnClickListener(v -> {
            // Handle decrease quantity logic
        });

        holder.removeButton.setOnClickListener(v -> {
            // Handle remove item logic
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, itemQuantity;
        ImageButton increaseButton, decreaseButton, removeButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
            increaseButton = itemView.findViewById(R.id.increaseButton);
            decreaseButton = itemView.findViewById(R.id.decreaseButton);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }
}
