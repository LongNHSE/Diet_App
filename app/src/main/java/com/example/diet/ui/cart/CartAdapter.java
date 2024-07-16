package com.example.diet.ui.cart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diet.R;
import com.example.diet.cart.dto.Cart;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<Cart> cartList;

    public CartAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        Log.d("Cart Adapter", "cart: "+cart.getProduct());
        if (cart.getProduct() != null) {
            Log.d("Cart Adapter", "cart: "+cart);
            holder.itemName.setText(cart.getProduct().getProductName());
            holder.itemPrice.setText(String.valueOf(cart.getUnitPrice()) + "đ");
            holder.itemQuantity.setText(String.valueOf(cart.getQuantity()));
            String[] images = cart.getProduct().getImages();
            if (images != null && images.length > 0) {
                Picasso.get().load(images[0]).into(holder.itemImage);
            }
        } else {
            // Handle case where product is null (optional, depending on your logic)
            holder.itemName.setText("Unknown Product");
            holder.itemPrice.setText("");
            holder.itemQuantity.setText("");
            holder.itemImage.setImageDrawable(null);  // or set a default image
        }
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public int calculateTotalPrice() {
        int total = 0;
        for (Cart cart : cartList) {
            total += cart.getTotal();
        }
        return total;
    }
    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName, itemPrice, itemQuantity, totalPrice;
        ImageButton increaseButton, decreaseButton, removeButton;



        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
            increaseButton = itemView.findViewById(R.id.increaseButton);
            decreaseButton = itemView.findViewById(R.id.decreaseButton);
            removeButton = itemView.findViewById(R.id.removeButton);
            totalPrice = itemView.findViewById(R.id.totalPrice);
        }
    }
}
