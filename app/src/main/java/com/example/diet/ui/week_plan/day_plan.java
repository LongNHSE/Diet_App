package com.example.diet.ui.week_plan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diet.R;
import com.example.diet.meal.dto.Meal;
import com.example.diet.meal.service.MealServiceImp;
import com.example.diet.response.ResponseDTO;
import com.example.diet.ui.meal_info.MealInfoActivity;
import com.example.diet.util.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link day_plan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class day_plan extends Fragment {

    private RecyclerView recyclerView;
    private MealDayDataAdapter adapter;
    private List<Meal> mealList;
    private TextView typeName4;
    private TextView typeName5;
    private int index;
    private TextView dayIndexTextView;

    private int dayIndex = 1; // Initial day count
    private String userId;

    private String dietId;

    private static final String ARG_USER_ID = "user_id";
    private static final String ARG_DIET_ID = "diet_id";


    public day_plan() {
        // Required empty public constructor
    }

    public static day_plan newInstance(String userId, String dietId) {
        day_plan fragment = new day_plan();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        args.putString(ARG_DIET_ID, dietId);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            userId = getArguments().getString(ARG_USER_ID);
            dietId = getArguments().getString(ARG_DIET_ID);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day_plan, container, false);

        // Find the increaseButton ImageView
        ImageView increaseButton = view.findViewById(R.id.increaseButton);
        ImageView decreseButton = view.findViewById(R.id.decreaseButton);


        // Set an OnClickListener to handle clicks

        // Set an OnClickListener to handle clicks
        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseDayIndex(v);
            }
        });

        decreseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseDayIndex(v);
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("day_plan", "onViewCreatedddd: " + dietId);
        dayIndexTextView = view.findViewById(R.id.day_index);
        dayIndexTextView.setText("Day " + dayIndex);
        recyclerView = view.findViewById(R.id.rv_day_plan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MealDayDataAdapter(new ArrayList<>(), new MealDayDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String mealId) {
                // Handle item click here, e.g., open detail activity with mealId
                Log.d("day_plan", "Clicked meal ID: " + mealId);
                Intent intent = new Intent(getActivity(), MealInfoActivity.class);
                intent.putExtra("mealId", mealId);
                startActivity(intent);
            }

            @Override
            public Meal getMeal(int position) {
                return null;
            }
        });
        recyclerView.setAdapter(adapter);

        typeName4 = view.findViewById(R.id.total_calo);
        typeName5 = view.findViewById(R.id.total_ingre);

        fetchMeal();

    }

    public void increaseDayIndex(View view) {
        dayIndex++;
        dayIndexTextView.setText("Day " + String.valueOf(dayIndex));
        fetchMeal();
    }

    // Method to handle decrease step button click
    public void decreaseDayIndex(View view) {
        if (dayIndex > 1) {
            dayIndex--;
            dayIndexTextView.setText("Day " + String.valueOf(dayIndex));
            fetchMeal();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchMeal();
    }

    private void fetchMeal() {
        MealServiceImp mealService = RetrofitClient.getClient(null).create(MealServiceImp.class);
        Call<ResponseDTO<List<Meal>>> call = mealService.getAllMealBasedOnDietIdAndDayIndex(dietId, dayIndex);
        call.enqueue(new Callback<ResponseDTO<List<Meal>>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseDTO<List<Meal>>> call, @NonNull Response<ResponseDTO<List<Meal>>> response) {
                if (response.body() != null) {
                    mealList = response.body().getData();
                    Log.d("day_plan", "onResponse: " + mealList);
                    adapter.updateData(mealList);
                    updateTextViews(mealList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseDTO<List<Meal>>> call, @NonNull Throwable t) {
                Log.e("day_plan", "onFailure: ", t);
            }
        });
    }

    private void updateTextViews(List<Meal> mealList) {
        int totalCalories = 0;
        int totalCarbs = 0;
        int totalFat = 0;
        int totalProtein = 0;

        for (Meal meal : mealList) {
            totalCalories += meal.getTotalCalstd(); // Assuming Meal has getTotalCalstd() method
            totalCarbs += meal.getCarbohydrated(); // Assuming Meal has getCarbohydrated() method
            totalFat += meal.getFat(); // Assuming Meal has getFat() method
            totalProtein += meal.getProtein(); // Assuming Meal has getProtein() method
        }

        typeName4.setText(totalCalories + " Calories");
        typeName5.setText(totalCarbs + "g Carbs, " + totalFat + "g Fat, " + totalProtein + "g Protein");
    }


}
