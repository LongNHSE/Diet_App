package com.example.diet.ui.week_plan;

import android.content.Intent;
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
import android.widget.TextView;

import com.example.diet.R;
import com.example.diet.meal.dto.Meal;
import com.example.diet.meal_standard.dto.MealStandard;
import com.example.diet.meal_structure.dto.MealStructure;
import com.example.diet.meal.service.MealServiceImp;
import com.example.diet.meal_frame.dto.MealFrame;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;
import com.example.diet.ui.meal_info.MealInfoActivity;

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
    private String dayid;
    private int index;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public day_plan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment day_plan.
     */
    // TODO: Rename and change types and number of parameters
    public static day_plan newInstance(String param1, String param2) {
        day_plan fragment = new day_plan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void navigateToMainActivity() {
        Log.d("MainActivity", "navigateToMainActivity");

//        startActivity(new Intent(this, MealInfoActivity.class));
//        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MealDayDataAdapter(new ArrayList<>());
        recyclerView = view.findViewById(R.id.rv_day_plan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        typeName4 = view.findViewById(R.id.total_calo);
        typeName5 = view.findViewById(R.id.total_ingre);
        fetchMeal();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day_plan, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();
        fetchMeal();

    }


    private void fetchMeal() {
        MealServiceImp MealService = RetrofitClient.getClient().create(MealServiceImp.class);
        Call<ResponseDTO<List<Meal>>> call = MealService.getAllMealBasedOnDay("668405a5ea609f9dc38c018f");
        Log.d("day_plan", "fetchMeal: " + call.request().url().toString());
        call.enqueue(new Callback<ResponseDTO<List<Meal>>>() {
            @Override
            public void onResponse(@NonNull  Call<ResponseDTO<List<Meal>>> call, @NonNull Response<ResponseDTO<List<Meal>>> response) {
                Log.d("day_plan", "Response: " + response.body());
                List<Meal> mealList = response.body().getData();
                Log.d("day_plan", "onResponse: " + mealList);
                adapter.updateData(mealList);
                updateTextViews(mealList);
            }

            @Override
            public void onFailure(Call<ResponseDTO<List<Meal>>> call, Throwable t) {

            }

        });

    }
    private void updateTextViews(List<Meal> mealList) {
        // Logic to update typeName4 and typeName5 based on mealList data
        String totalCalories = "";
        String totalIngredients = "";
        for (Meal meal : mealList) {
            totalCalories = meal.getTotalCalstd() + " Calories. "; // Assuming Meal has a getCalories() method
            totalIngredients = meal.getCarbohydrated() + "g Carbs, " + meal.getFat() + "g Fat, " + meal.getProtein() + "g Protein. "; // Assuming Meal has a getIngredients() method
        }
        typeName4.setText(String.valueOf(totalCalories));
        typeName5.setText(String.valueOf(totalIngredients));
    }
}
