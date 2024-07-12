package com.example.diet.ui.week_plan;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;
import com.example.diet.food_type.dto.food_type;
import com.example.diet.food_type.service.FoodTypeServiceImp;
import com.example.diet.meal.dto.Meal;
import com.example.diet.meal.service.MealServiceImp;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;
import com.example.diet.week_item.dto.WeekItem;
import com.example.diet.week_item.service.WeekItemImp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link week_plan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class week_plan extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private RecyclerView recyclerView;
    private FoodTypeWeekDataAdapter adapter;

    private int weekIndex = 1;
    private TextView weekIndexTextView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public week_plan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment week_plan.
     */
    // TODO: Rename and change types and number of parameters
    public static week_plan newInstance(String param1, String param2) {
        week_plan fragment = new week_plan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        adapter = new FoodTypeWeekDataAdapter(new ArrayList<>(), getContext(),
                new OnItemClickListener2() {
                    @Override
                    public void onItemClick(String id) {
                        Log.d("TAG", "onItemClick: " + id);
                        //Intent intent = new Intent(getContext(), MealInfoActivity.class);
                    }
                }
        );
        weekIndexTextView = view.findViewById(R.id.week_index);
        weekIndexTextView.setText("Week " + String.valueOf(weekIndex));
        recyclerView = view.findViewById(R.id.rv_week_plan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        fetchFoodType();
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchFoodType();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week_plan, container, false);

        ImageView increaseButton = view.findViewById(R.id.increaseButtonWeek);
        ImageView decreseButton = view.findViewById(R.id.decreaseButtonWeek);

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseWeekIndex(v);
            }
        });

        decreseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseWeekIndex(v);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    private void decreaseWeekIndex(View v) {
        if (weekIndex > 1) {
            weekIndex--;
            weekIndexTextView.setText("Week " + String.valueOf(weekIndex));
            fetchFoodType();
        }
    }

    private void increaseWeekIndex(View v) {
        weekIndex++;
        weekIndexTextView.setText("Week " + String.valueOf(weekIndex));
        fetchFoodType();
    }

    private void fetchFoodType() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_prefs", getContext().MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        String dietId = sharedPreferences.getString("dietId", "");
        WeekItemImp weekItemImp = RetrofitClient.getClient(token).create(WeekItemImp.class);
        Call<ResponseDTO<List<WeekItem>>> call = weekItemImp.getWeekItem(dietId, weekIndex);
        call.enqueue(new Callback<ResponseDTO<List<WeekItem>>>() {
            @Override
            public void onResponse(Call<ResponseDTO<List<WeekItem>>> call, Response<ResponseDTO<List<WeekItem>>> response) {
                if (response.body().getData() != null) {
                    List<WeekItem> weekItemList = response.body().getData();
                    adapter.updateData(weekItemList);
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<List<WeekItem>>> call, Throwable t) {

            }


        });
    }
}