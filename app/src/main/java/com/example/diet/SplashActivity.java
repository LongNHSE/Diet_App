package com.example.diet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "prefs";
    private static final String KEY_FIRST_START = "first_start";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if it's the user's first time launching the app
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean firstStart = preferences.getBoolean(KEY_FIRST_START, true);

        if (firstStart) {
            setContentView(R.layout.activity_splash);

            // Find the button by its ID
            Button getStartedButton = findViewById(R.id.startButton);

            // Set an OnClickListener to the button
            getStartedButton.setOnClickListener(v -> {
                // Update SharedPreferences to indicate that the user has clicked "Get Started"
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(KEY_FIRST_START, false);
                editor.apply();

                // Create an Intent to start IntroSliderActivity
                Intent intent = new Intent(SplashActivity.this, IntroSliderActivity.class);
                startActivity(intent);
                finish(); // Optional: Close this activity once the button is clicked
            });
        } else {
            // If not the first start, redirect to SignInActivity
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close this activity
        }
    }
}
