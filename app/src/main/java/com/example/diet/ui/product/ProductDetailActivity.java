package com.example.diet.ui.product;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.diet.databinding.ActivityProductDetailBinding;
import com.example.diet.product.dto.Product;
import com.example.diet.product.service.ProductServiceImp;
import com.example.diet.util.RetrofitClient;
import com.example.diet.util.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetailActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ImageCarouselAdapter imageAdapter;


    private ProductAdapter productAdapter;

    private ProductAlternativeAdapter productAlternativeAdapter;


    private Product product;


    private String productId;


    private ActivityProductDetailBinding binding;


    private Retrofit retrofit;
    private ProductServiceImp apiService;
    private List<Product> relatedProducts;

    private List<Product> alternativeProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (getIntent() != null) {
            productId = getIntent().getStringExtra("productID");
            Log.d("productID target: ", productId);
        }


        retrofit = RetrofitClient.getClient(null);
        apiService = retrofit.create(ProductServiceImp.class);


        // Initialize products list and adapter
        relatedProducts = new ArrayList<>();
        productAdapter = new ProductAdapter(relatedProducts, ProductDetailActivity.this);
        productAlternativeAdapter = new ProductAlternativeAdapter(alternativeProducts, ProductDetailActivity.this);


        // Set up RecyclerView
        RecyclerView recyclerView = binding.recyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productAdapter);


        if (productId != null) {
            loadProduct(productId);

        }
    }


    private void loadProduct(String productId) {
        Retrofit retrofit = RetrofitClient.getClient(null);
        ProductServiceImp apiService = retrofit.create(ProductServiceImp.class);


        Call<JsonObject> call = apiService.getProductById(productId);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject.has("statusCode") && jsonObject.get("statusCode").getAsInt() == 200) {
                        JsonObject data = jsonObject.getAsJsonObject("data");
                        Gson gson = new Gson();
                        Product product = gson.fromJson(data, Product.class);


                        // Setting up the ImageCarouselAdapter
                        ImageCarouselAdapter imageAdapter = new ImageCarouselAdapter(Arrays.asList(product.getImages()), ProductDetailActivity.this);
                        binding.imageView.setAdapter(imageAdapter);


                        // Setting Data
                        binding.tvProductNameDetail.setText(product.getProductName());
                        binding.tvRating.setText(String.valueOf(product.getRate()));
                        binding.tvPrice.setText(Utils.formatNumberWithDelimiters(product.getPrice()));
                        binding.tvProductEffect.setText(product.getEffect());
                        binding.brandData.setText(product.getBrand());
                        binding.originData.setText(product.getOrigin());
                        binding.volumeData.setText(String.valueOf(product.getVolume()));

                        getAlternativeProduct(product.getProductTypeId());


                        loadRelatedProducts(product.getType());
                    } else {
                        Log.d("Loading Product Details ID: " + productId, "Error: statusCode not 200");
                    }
                } else {
                    Log.d("Loading Product Details ID: " + productId, "Error: response unsuccessful or body null");
                }
            }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Loading Product Details ID: " + productId, "Failed", t);
            }
        });
    }

    private void getAlternativeProduct(String catergoryId) {
        Retrofit retrofit = RetrofitClient.getClient(null);
        ProductServiceImp apiService = retrofit.create(ProductServiceImp.class);
        Call<JsonObject> call = apiService.getRelatedProduct(catergoryId);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject.has("statusCode") && jsonObject.get("statusCode").getAsInt() == 200) {
                        JsonArray dataArray = jsonObject.getAsJsonArray("data");
                        List<Product> returnProducts = new ArrayList<>();
                        Gson gson = new Gson();
                        for (JsonElement element : dataArray) {
                            Product product = gson.fromJson(element, Product.class);
                            returnProducts.add(product);
                        }
                        relatedProducts.clear();
                        relatedProducts.addAll(returnProducts);
                        productAdapter.notifyDataSetChanged();
                    } else {
                        Log.d("Loading Products:", "error");
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Loading Products:", "failed");
            }
        });
    }

    private void loadRelatedProducts(String type) {
        Retrofit retrofit = RetrofitClient.getClient(null);
        ProductServiceImp apiService = retrofit.create(ProductServiceImp.class);


        Call<JsonObject> call = apiService.getRelatedProduct(type); // Make sure the API service method returns Call<JsonObject>
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject.has("statusCode") && jsonObject.get("statusCode").getAsInt() == 200) {
                        JsonArray dataArray = jsonObject.getAsJsonArray("data");
                        List<Product> returnProducts = new ArrayList<>();


                        Gson gson = new Gson();
                        for (JsonElement element : dataArray) {
                            Product product = gson.fromJson(element, Product.class);
                            returnProducts.add(product);
                        }
                        relatedProducts.clear();
                        relatedProducts.addAll(returnProducts);
                        productAdapter.notifyDataSetChanged();
                    } else {
                        Log.d("Loading Products:", "error");
                    }
                }
            }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Loading Products:", "failed");
            }
        });
    }

}