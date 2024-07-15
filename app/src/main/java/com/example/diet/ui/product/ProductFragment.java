package com.example.diet.ui.product;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.diet.databinding.ActivityProductBinding;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.diet.product.dto.Product;
import com.example.diet.product.service.ProductServiceImp;
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

public class ProductFragment extends Fragment {

    private ActivityProductBinding  binding;
    private List<Product> products;
    private ProductAdapter productAdapter;

    public ProductFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize products list and productAdapter
        products = new ArrayList<>();
        productAdapter = new ProductAdapter(products, requireActivity());

        // Set up RecyclerView
        RecyclerView recyclerView = binding.recyclerView;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), 2); // 2 columns
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productAdapter);

        // Load products
        loadProducts();
    }

    private void loadProducts() {
        Retrofit retrofit = RetrofitClient.getClient(null);
        ProductServiceImp apiService = retrofit.create(ProductServiceImp.class);

        Call<JsonObject> call = apiService.getAllProducts();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject.has("statusCode") && jsonObject.get("statusCode").getAsInt() == 200) {
                        JsonArray dataArray = jsonObject.getAsJsonArray("data");
                        List<Product> returnedProducts = new ArrayList<>();

                        Gson gson = new Gson();
                        for (JsonElement element : dataArray) {
                            Product product = gson.fromJson(element, Product.class);
                            returnedProducts.add(product);
                        }

                        // Update RecyclerView
                        products.addAll(returnedProducts);
                        productAdapter.notifyDataSetChanged();

                    } else {
                        Log.d("Loading Products:", "Failed to get data from API");
                    }
                } else {
                    Log.d("Loading Products:", "Failed API response");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Loading Products:", "failed");
            }
        });
    }
}
