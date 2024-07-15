package com.example.diet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DietSuccessActivity extends AppCompatActivity {

    private ImageView successImage;
    private TextView successMessage;
    private Button previousButton, continueButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_diet_success);

        successImage = findViewById(R.id.successImage);
        successMessage = findViewById(R.id.successMessage);
        previousButton = findViewById(R.id.previousButton);
        continueButton = findViewById(R.id.continueButton);

        // Load the success image using Glide
        Glide.with(this)
                .load(R.drawable.diet_sucesss)
                .override(600, 600)
                .into(successImage);

        // Set the success message
        successMessage.setText("Diet setup successfully!");

        previousButton.setOnClickListener(v -> finish());

        continueButton.setOnClickListener(v -> {
            Intent intent = new Intent(DietSuccessActivity.this, GeneratingPlanActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
