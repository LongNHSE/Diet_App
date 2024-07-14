package com.example.diet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

public class ActivityLevelSuccessActivity extends AppCompatActivity {

    private MaterialButton finishButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_level_success);

        finishButton = findViewById(R.id.finishButton);
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        finishButton.setOnClickListener(v -> {
            // Log all shared preferences in JSON format
            logSharedPreferences(sharedPreferences);

            Intent intent = new Intent(ActivityLevelSuccessActivity.this, GeneratingPlanActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void logSharedPreferences(SharedPreferences sharedPreferences) {
        Gson gson = new Gson();
        String json = gson.toJson(sharedPreferences.getAll());
        Log.d("SharedPreferences", json);
    }
}
