package com.example.diet.ui.food_fact;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;
import com.example.diet.databinding.ActivityEntryBinding;
import com.example.diet.food_fact.dto.FoodFact;
import com.example.diet.food_fact.service.FoodFactServiceImp;
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

public class EntryActivity extends AppCompatActivity {


    private ActivityEntryBinding binding;
    private List<FoodFact> facts;
    private FoodFactAdapter foodFactAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEntryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        EdgeToEdge.enable(this);


        // Initialize products list and productAdapter
        facts = new ArrayList<>();
        foodFactAdapter = new FoodFactAdapter(facts, EntryActivity.this);


        // Set up RecyclerView
        RecyclerView recyclerView = binding.FoodFactRecyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
//        int itemSpacing = getResources().getDimensionPixelSize(R.dimen.item_spacing);
//        recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(itemSpacing));
        recyclerView.setAdapter(foodFactAdapter);


        // Load products
        loadFoodFacts();
    }


    private void loadFoodFacts() {
        Log.d("test", "loadingggggg");
        Retrofit retrofit = RetrofitClient.getClient(null);
        FoodFactServiceImp apiService = retrofit.create(FoodFactServiceImp.class);


        Call<JsonObject> call = apiService.getAllFoodFacts();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject.has("statusCode") && jsonObject.get("statusCode").getAsInt() == 200) {
                        JsonArray dataArray = jsonObject.getAsJsonArray("data");
                        List<FoodFact> returnedFacts = new ArrayList<>();


                        Gson gson = new Gson();
                        for (JsonElement element : dataArray) {
                            FoodFact fact = gson.fromJson(element, FoodFact.class);
                            returnedFacts.add(fact);
                        }


                        // Update RecyclerView
                        facts.addAll(returnedFacts);
                        foodFactAdapter.notifyDataSetChanged();


                        Log.d("Loading Facts:", "Number of products: " + facts.size());
                        for (FoodFact fact : facts) {
                            Log.d("Fact:", fact.toString());
                        }
                    } else {
                        Log.d("Loading Facts:", "Failed to get data from API");
                    }
                } else {
                    Log.d("Loading Facts:", "Failed API response");
                }
            }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Loading Facts:", "Failed to fetch data from API");
            }
        });
    }


}
