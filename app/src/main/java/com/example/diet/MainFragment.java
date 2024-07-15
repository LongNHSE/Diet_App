package com.example.diet;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.diet.diet.dto.diet;
import com.example.diet.diet.service.DietServiceImp;
import com.example.diet.response.ResponseDTO;
import com.example.diet.ui.meal_info.MealInfoActivity;
import com.example.diet.util.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;

    private String userId;
    private String dietId;

    private FragmentPageAdapter adapter;
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1;

    public MainFragment() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
        startActivity(new Intent( requireActivity(), MealInfoActivity.class));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission( requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( requireActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
        }
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout =  requireActivity().findViewById(R.id.tab);
        viewPager2 =  requireActivity().findViewById(R.id.view_page);


        SharedPreferences preferences =  requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        userId = preferences.getString("userId", "0");

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });

        getLatestDiet();
        if (dietId == null) {
            // Redirect to Create diet
            // Intent intent = new Intent(this, CreateDietActivity.class);
        }
    }

    public void renderFragment() {
        adapter = new FragmentPageAdapter( requireActivity().getSupportFragmentManager(), getLifecycle(), userId, dietId);
        viewPager2.setAdapter(adapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Update selected item in BottomNavigationView based on ViewPager2 position

            }
        });

        TabLayout.Tab tab1 = tabLayout.newTab();
        tab1.setText("Day");
        tabLayout.addTab(tab1);

        TabLayout.Tab tab2 = tabLayout.newTab();
        tab2.setText("Week");
        tabLayout.addTab(tab2);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(position == 0 ? "Day" : "Week")
        ).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                TextView textView =  requireActivity().findViewById(R.id.title);
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
        SharedPreferences preferences =  requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String jwt = preferences.getString("token", null);
        Log.d("MainActivity", "JWT: " + jwt);

        DietServiceImp dietServiceImp = RetrofitClient.getClient(jwt).create(DietServiceImp.class);
        Call<ResponseDTO<diet>> call = dietServiceImp.getLatestDiet();

        call.enqueue(new Callback<ResponseDTO<diet>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseDTO<diet>> call, @NonNull Response<ResponseDTO<diet>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    diet diet = response.body().getData();
                    if (diet != null) {
                        dietId = diet.getId();
                        preferences.edit().putString("dietId", dietId).apply();
                        Log.d("MainActivity", "Diet ID: " + dietId);
                        renderFragment();
                    } else {
                        dietId = null;
                    }
                } else {
                    Log.e("MainActivity", "Response unsuccessful or body is null" + response);
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<diet>> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }
}
