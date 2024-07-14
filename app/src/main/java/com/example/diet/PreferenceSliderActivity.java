package com.example.diet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.diet.preference.dto.PreferenceResponse;
import com.example.diet.preference.services.PreferenceService;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;
import com.example.diet.week_item.CircularPreferenceSliderAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreferenceSliderActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TextView preferenceNameTextView;
    private TextView preferenceDescriptionTextView;
    private List<PreferenceResponse> preferenceList;
    private CircularPreferenceSliderAdapter adapter;
    private Button previousButton, selectButton, nextButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_slider);

        viewPager = findViewById(R.id.viewPager);
        preferenceNameTextView = findViewById(R.id.preferenceNameTextView);
        preferenceDescriptionTextView = findViewById(R.id.preferenceDescriptionTextView);
        previousButton = findViewById(R.id.previousButton);
        selectButton = findViewById(R.id.selectButton);
        nextButton = findViewById(R.id.nextButton);

        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);

        fetchPreferences(token);

        previousButton.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            viewPager.setCurrentItem(currentItem - 1);
        });

        selectButton.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            PreferenceResponse selectedPreference = preferenceList.get(currentItem % preferenceList.size());
            Toast.makeText(PreferenceSliderActivity.this, "Selected: " + selectedPreference.getName(), Toast.LENGTH_SHORT).show();

            // Store the selected preferenceId in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("selectedPreferenceId", selectedPreference.get_id());
            editor.apply();

            // Redirect to PreferenceSuccessActivity
            Intent intent = new Intent(PreferenceSliderActivity.this, PreferenceSuccessActivity.class);
            startActivity(intent);
            finish();
        });

        nextButton.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            viewPager.setCurrentItem(currentItem + 1);
        });
    }

    private void fetchPreferences(String token) {
        PreferenceService preferenceService = RetrofitClient.getClient(token).create(PreferenceService.class);
        Call<ResponseDTO<List<PreferenceResponse>>> call = preferenceService.getPreferences();

        call.enqueue(new Callback<ResponseDTO<List<PreferenceResponse>>>() {
            @Override
            public void onResponse(Call<ResponseDTO<List<PreferenceResponse>>> call, Response<ResponseDTO<List<PreferenceResponse>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    preferenceList = response.body().getData();
                    setupViewPager();
                } else {
                    Toast.makeText(PreferenceSliderActivity.this, "Failed to fetch preferences", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<List<PreferenceResponse>>> call, Throwable t) {
                Toast.makeText(PreferenceSliderActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupViewPager() {
        adapter = new CircularPreferenceSliderAdapter(this, preferenceList);
        viewPager.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> updatePreferenceDetails(position % preferenceList.size()));

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updatePreferenceDetails(position % preferenceList.size());
            }
        });

        if (!preferenceList.isEmpty()) {
            viewPager.setCurrentItem(preferenceList.size() * 50, false);
            updatePreferenceDetails(preferenceList.size() * 50 % preferenceList.size());
        }
    }

    private void updatePreferenceDetails(int position) {
        PreferenceResponse preference = preferenceList.get(position);
        preferenceNameTextView.setText(preference.getName());
        preferenceDescriptionTextView.setText(preference.getDescription());
    }
}
