package com.example.diet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.diet.exercise.dto.service.dto.Exercise;
import com.example.diet.exercise.dto.service.service.ExerciseServiceImp;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ExerciseDetailActivity extends AppCompatActivity {

    private static final String TAG = "ExerciseDetailActivity";
    private TextView exerciseName;
    private ImageView exerciseImage;
    private TextView exerciseCalories;
    private TextView exerciseDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        // Find the views
        exerciseName = findViewById(R.id.exercise_name);
        exerciseImage = findViewById(R.id.exerciseImage);
        exerciseCalories = findViewById(R.id.exercise_calories);
        exerciseDescription = findViewById(R.id.exercise_description);

        // Find the back button ImageView
        ImageView backButton = findViewById(R.id.back_button);

        // Set an OnClickListener on the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity
                Intent intent = new Intent(ExerciseDetailActivity.this, MainFragment.class);
                startActivity(intent);
                finish();
            }
        });

        // Fetch the authToken from intent or other source
        String authToken = "your_auth_token";

        // Fetch exercise data
        fetchExerciseData(authToken);
    }

    private void fetchExerciseData(String authToken) {
        String exerciseId = "6672bdd6e220b5db05a1c5cc";

        Retrofit retrofit = RetrofitClient.getClient(authToken);
        ExerciseServiceImp service = retrofit.create(ExerciseServiceImp.class);

        Call<ResponseDTO<Exercise>> call = service.getMealById(exerciseId);
        call.enqueue(new Callback<ResponseDTO<Exercise>>() {
            @Override
            public void onResponse(Call<ResponseDTO<Exercise>> call, Response<ResponseDTO<Exercise>> response) {
                if (response.isSuccessful()) {
                    Exercise exercise = response.body().getData();
                    // Update UI with exercise data
                    updateUI(exercise);
                } else {
                    Log.e(TAG, "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<Exercise>> call, Throwable t) {
                Log.e(TAG, "Failure: " + t.getMessage());
            }
        });
    }

    private void updateUI(Exercise exercise) {
        // Split the exName by comma and take the first part
        String[] exNameParts = exercise.getExName().split(",");
        String exName = exNameParts[0].trim(); // trim() to remove any leading or trailing spaces

        exerciseName.setText(exName);
        exerciseCalories.setText(String.valueOf(exercise.getCalorexp()) + " kcal");

        // Split the description into lines with dashes
        String[] descriptionParts = exercise.getDescription().split("\\.\\s+");
        StringBuilder descriptionWithDashes = new StringBuilder();
        for (String part : descriptionParts) {
            descriptionWithDashes.append("- ").append(part.trim()).append("\n\n");
        }
        exerciseDescription.setText(descriptionWithDashes.toString().trim());

        Glide.with(this)
                .load(exercise.getIcon())

                .into(exerciseImage);
    }

}
