package com.example.diet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_day_plan);

        ImageView searchImageView = findViewById(R.id.search_1);
        searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ExerciseActivity", "Search icon clicked");
                Intent intent = new Intent(ExerciseActivity.this, ExerciseDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
