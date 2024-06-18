package com.example.diet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityExercises extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_withexercises);

        // Find the search icon ImageViews
        ImageView searchIcon1 = findViewById(R.id.search_1);
        ImageView searchIcon2 = findViewById(R.id.search_2);
        ImageView searchIcon3 = findViewById(R.id.search_3);

        // Set OnClickListeners on the search icons
        View.OnClickListener searchClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ExerciseDetailActivity
                Intent intent = new Intent(MainActivityExercises.this, ExerciseDetailActivity.class);
                startActivity(intent);
            }
        };

        searchIcon1.setOnClickListener(searchClickListener);
        searchIcon2.setOnClickListener(searchClickListener);
        searchIcon3.setOnClickListener(searchClickListener);
    }
}