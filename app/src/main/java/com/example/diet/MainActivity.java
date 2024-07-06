package com.example.diet;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.diet.diet.dto.diet;
import com.example.diet.diet.service.DietServiceImp;
import com.example.diet.response.ResponseDTO;
import com.example.diet.ui.meal_info.MealInfoActivity;
import com.example.diet.ui.week_plan.day_plan;
import com.example.diet.util.RetrofitClient;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    private day_plan dayPlanFragment;

    private String userId;

    private String dietId;

    private FragmentPageAdapter adapter;
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with your logic
            } else {
                // Permission denied, handle appropriately
            }
        }
    }

    public void navigateToMealInfo(View view) {
        startActivity(new Intent(this, MealInfoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
        }
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tabLayout = findViewById(R.id.tab);
        viewPager2 = findViewById(R.id.view_page);

        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = preferences.getString("userId", "0");

        getLatestDiet();
        if (dietId == null) {
            //Redirect to Create diet
//            Intent intent = new Intent(this, CreateDietActivity.class);
        }


    }

    public void renderFragment() {
        adapter = new FragmentPageAdapter(getSupportFragmentManager(), getLifecycle(), userId, dietId);
        viewPager2.setAdapter(adapter);

        TabLayout.Tab tap1 = tabLayout.newTab();
        tap1.setText("Day");
        tabLayout.addTab(tap1);

        TabLayout.Tab tap2 = tabLayout.newTab();
        tap2.setText("Week");
        tabLayout.addTab(tap2);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                TextView textView = findViewById(R.id.title);
                if (tab.getPosition() == 0) {
                    textView.setText("Meal and Exercise Plan");
                } else {
                    textView.setText("Meal Plan");
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Handle tab unselected
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Handle tab reselected
            }
        });
    }

    public void getLatestDiet() {

        SharedPreferences preferences = getSharedPreferences("jwt", Context.MODE_PRIVATE);
        String jwt = preferences.getString("jwt", null);

        // Get latest diet from server
        DietServiceImp dietServiceImp = RetrofitClient.getClient("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2NjU4MzUwZGU5ZDRhNmM3YWU4OTg1NGMiLCJpYXQiOjE3MjAyMDMwNDYsImV4cCI6MTcyMDgwNzg0Nn0.Io-61XowSbv6BWDFzio4k3K1yNNorYDq117GRStX2xk").create(DietServiceImp.class);
        Call<ResponseDTO<diet>> call = dietServiceImp.getLatestDiet();

        call.enqueue(new Callback<ResponseDTO<diet>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseDTO<diet>> call, @NonNull Response<ResponseDTO<diet>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    diet diet = response.body().getData();

                    if (diet != null) {
                        dietId = diet.getId();
                        renderFragment();
                    } else {
                        dietId = null;
                    }
                } else {
                    Log.e("MainActivity", "Response unsuccessful or body is null");
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<diet>> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });

    }
}