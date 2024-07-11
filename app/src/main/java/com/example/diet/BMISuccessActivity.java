package com.example.diet;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class BMISuccessActivity extends AppCompatActivity {

    private ImageView successImage;
    private TextView bmiCategory;
    private TextView bmiValue;
    private MaterialButton continueButton;
    private double bmi;
    private String userId;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_success);

        successImage = findViewById(R.id.successImage);
        bmiCategory = findViewById(R.id.bmiCategory);
        bmiValue = findViewById(R.id.bmiValue);
        continueButton = findViewById(R.id.continueButton);

        bmi = getIntent().getDoubleExtra("BMI_VALUE", 0.0);
        userId = getIntent().getStringExtra("userId");
        token = getIntent().getStringExtra("token");

        updateBMIResult(bmi);

        continueButton.setOnClickListener(v -> {
            Intent intent = new Intent(BMISuccessActivity.this, GoalSetupActivity.class);
            intent.putExtra("userId", userId);
            intent.putExtra("token", token);
            startActivity(intent);
            finish();
        });
    }

    private void updateBMIResult(double bmi) {
        int imageResId;
        String bmiText;

        if (bmi < 18.5) {
            imageResId = R.drawable.skinny;
            bmiText = "You are underweight";
            bmiCategory.setTextColor(ContextCompat.getColor(this, R.color.red));
            bmiCategory.setTypeface(null, Typeface.BOLD);
        } else if (bmi >= 18.5 && bmi < 24.9) {
            imageResId = R.drawable.balanced;
            bmiText = "You are healthy";
        } else if (bmi >= 25 && bmi < 29.9) {
            imageResId = R.drawable.fat;
            bmiText = "You are overweight";
            bmiCategory.setTextColor(ContextCompat.getColor(this, R.color.red));
            bmiCategory.setTypeface(null, Typeface.BOLD);
        } else {
            imageResId = R.drawable.obese;
            bmiText = "You are obese";
            bmiCategory.setTextColor(ContextCompat.getColor(this, R.color.red));
            bmiCategory.setTypeface(null, Typeface.BOLD);
        }

        Glide.with(this)
                .load(imageResId)
                .override(600, 600) // Resize the image to 600x600 pixels
                .into(successImage);

        bmiCategory.setText(bmiText);
        bmiValue.setText(String.format("Your BMI is: %.2f", bmi)); // Display the BMI value
    }
}
