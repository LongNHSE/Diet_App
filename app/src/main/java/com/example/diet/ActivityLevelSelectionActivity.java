package com.example.diet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.diet.activity_level.dto.ActivityLevelResponse;
import com.example.diet.activity_level.services.ActivityLevelService;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLevelSelectionActivity extends AppCompatActivity {

    private LinearLayout activityLevelsContainer;
    private ImageView selectedActivityImage;
    private TextView selectedActivityName;
    private MaterialButton previousButton, continueButton;

    private ActivityLevelService activityLevelService;

    private Map<String, Integer> activityLevelImages;
    private String selectedActivityLevelId;
    private String selectedActivityLevelName;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_level_selection);

        activityLevelsContainer = findViewById(R.id.activityLevelsContainer);
        selectedActivityImage = findViewById(R.id.selectedActivityImage);
        selectedActivityName = findViewById(R.id.selectedActivityName);
        previousButton = findViewById(R.id.previousButton);
        continueButton = findViewById(R.id.continueButton);

        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        activityLevelService = RetrofitClient.getClient(token).create(ActivityLevelService.class);

        initializeActivityLevelImages();
        fetchActivityLevels();

        previousButton.setOnClickListener(v -> onBackPressed());

        continueButton.setOnClickListener(v -> {
            if (selectedActivityLevelId == null) {
                Toast.makeText(ActivityLevelSelectionActivity.this, "Please select an activity level", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("activityLevelId", selectedActivityLevelId);
                editor.putString("activityLevelName", selectedActivityLevelName);
                editor.apply();

                Intent intent = new Intent(ActivityLevelSelectionActivity.this, ActivityLevelSuccessActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeActivityLevelImages() {
        activityLevelImages = new HashMap<>();
        activityLevelImages.put("Sedentary", R.drawable.sedentary);
        activityLevelImages.put("Lightly Active", R.drawable.lightly_active);
        activityLevelImages.put("Moderately Active", R.drawable.moderately_active);
        activityLevelImages.put("Active", R.drawable.active);
        activityLevelImages.put("Very Active", R.drawable.very_active);
    }

    private void fetchActivityLevels() {
        Call<ResponseDTO<List<ActivityLevelResponse>>> call = activityLevelService.getActivityLevels();
        call.enqueue(new Callback<ResponseDTO<List<ActivityLevelResponse>>>() {
            @Override
            public void onResponse(Call<ResponseDTO<List<ActivityLevelResponse>>> call, Response<ResponseDTO<List<ActivityLevelResponse>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ActivityLevelResponse> activityLevels = response.body().getData();
                    displayActivityLevels(activityLevels);
                } else {
                    Toast.makeText(ActivityLevelSelectionActivity.this, "Failed to fetch activity levels", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<List<ActivityLevelResponse>>> call, Throwable t) {
                Toast.makeText(ActivityLevelSelectionActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayActivityLevels(List<ActivityLevelResponse> activityLevels) {
        for (ActivityLevelResponse level : activityLevels) {
            TextView textView = new TextView(this);
            textView.setText(level.getLevelName());
            textView.setTextSize(18);
            textView.setPadding(16, 16, 16, 16);
            textView.setBackgroundResource(R.drawable.unselected_background);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins(0, 15, 0, 15);

            textView.setOnClickListener(v -> {
                selectedActivityLevelId = level.get_id();
                selectedActivityLevelName = level.getLevelName();
                selectedActivityName.setText(level.getLevelName());
                selectedActivityName.setVisibility(View.VISIBLE);

                // Load the image from local resources
                Integer imageResId = activityLevelImages.get(level.getLevelName());
                if (imageResId != null) {
                    Glide.with(this).load(imageResId).into(selectedActivityImage);
                    selectedActivityImage.setVisibility(View.VISIBLE);
                } else {
                    selectedActivityImage.setVisibility(View.GONE);
                }

                // Mark the selected item visually
                for (int i = 0; i < activityLevelsContainer.getChildCount(); i++) {
                    View child = activityLevelsContainer.getChildAt(i);
                    child.setBackgroundResource(R.drawable.unselected_background);
                }
                textView.setBackgroundResource(R.drawable.selected_background);
            });

            activityLevelsContainer.addView(textView);
        }
    }
}
