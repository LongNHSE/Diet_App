package com.example.diet.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;
import com.example.diet.cart.dto.Cart;
import com.example.diet.cart.services.CartService;
import com.example.diet.payment.dto.Payment;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity {

    private CartService cartService;
    private CartAdapter cartAdapter;
    private RecyclerView recyclerView;
    private TextView totalPriceTextView;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String jwt = preferences.getString("token", null);
        Log.d("CartActivity", "JWT: " + jwt);

        cartService = RetrofitClient.getClient(jwt).create(CartService.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        totalPriceTextView = findViewById(R.id.totalPrice);
        checkoutButton = findViewById(R.id.checkoutButton);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = RetrofitClient.getClient(jwt);
                CartService cartService1 = retrofit.create(CartService.class);
                Call<ResponseDTO<Payment>> call = cartService1.createPaymentLink();
                call.enqueue(new Callback<ResponseDTO<Payment>>() {
                    @Override
                    public void onResponse(Call<ResponseDTO<Payment>> call, Response<ResponseDTO<Payment>> response) {
                        if (response.body() != null) {
                            String url = response.body().getData().getCheckoutUrl();
                            Log.d("CartActivity", "Payment URL: " + url);
                            openPaymentLink(url);
                        } else {
                            Log.e("CartActivity", "Payment URL is null");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDTO<Payment>> call, Throwable t) {
                        Log.e("CartActivity", "Error: " + t.getMessage());
                    }
                });
            }
        });

        // Fetch cart details and update cart concurrently
        fetchCartDetails();

        String productId = getIntent().getStringExtra("productID");
        int productPrice = getIntent().getIntExtra("productPrice", 0);

        if (productId != null) {
            Cart cart = new Cart(productId, 1); // Adjust this as per your Cart constructor

            // Update cart details
            patchProductDetail(cart);
        }
    }

    private void patchProductDetail(Cart cart) {
        Call<Cart> call = cartService.updateProductDetail(cart);
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    Cart updatedCart = response.body();
                    Log.d("CartActivity", "Cart updated: " + updatedCart);
                } else {
                    Log.e("CartActivity", "Failed to update cart");
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.e("CartActivity", "Error: " + t.getMessage());
            }
        });
    }

    private void fetchCartDetails() {
        Call<JsonObject> call = cartService.getProductDetails();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject.has("statusCode") && jsonObject.get("statusCode").getAsInt() == 200) {
                        JsonArray dataArray = jsonObject.getAsJsonArray("data");
                        List<Cart> cartList = new ArrayList<>();

                        Gson gson = new Gson();
                        for (JsonElement element : dataArray) {
                            Cart cart = gson.fromJson(element, Cart.class);
                            cartList.add(cart);
                        }

                        // Update RecyclerView
                        cartAdapter = new CartAdapter(CartActivity.this, cartList);
                        recyclerView.setAdapter(cartAdapter);
                        Log.d("CartActivity", "Cart details fetched successfully");
                        // Calculate and set total price
                        int totalPrice = cartAdapter.calculateTotalPrice();
                        totalPriceTextView.setText(String.valueOf(totalPrice) + "đ");
                    } else {
                        Log.e("CartActivity", "Failed to get data from API");
                    }
                } else {
                    Log.e("CartActivity", "Failed API response");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("CartActivity", "Error: " + t.getMessage(), t);
            }
        });
    }

    private void openPaymentLink(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
