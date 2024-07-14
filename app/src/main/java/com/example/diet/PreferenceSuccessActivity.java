package com.example.diet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class PreferenceSuccessActivity extends AppCompatActivity {

    private ImageView successImage;
    private TextView successMessage;
    private Button previousButton, continueButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_success);

        successImage = findViewById(R.id.successImage);
        successMessage = findViewById(R.id.successMessage);
        previousButton = findViewById(R.id.previousButton);
        continueButton = findViewById(R.id.continueButton);
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // Load the success image using Glide
        Glide.with(this)
                .load(R.drawable.preferences_success)
                .override(600, 600)  // Resize the image to improve performance
                .into(successImage);

        // Set the success message
        successMessage.setText("Preference setup successfully!");

        previousButton.setOnClickListener(v -> {
            finish();
        });

        continueButton.setOnClickListener(v -> {
            Intent intent = new Intent(PreferenceSuccessActivity.this, GeneratingPlanActivity.class);
            startActivity(intent);
            logSharedPreferences(sharedPreferences);
            finish();
        });
    }

    private void logSharedPreferences(SharedPreferences sharedPreferences) {
        Gson gson = new Gson();
        String json = gson.toJson(sharedPreferences.getAll());
        Log.d("SharedPreferences", json);
    }
}
