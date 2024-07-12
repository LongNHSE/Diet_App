package com.example.diet.ui.week_plan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diet.R;
import com.example.diet.food_detail.dto.FoodDetail;
import com.example.diet.ui.meal_info.OnItemClickListener;
import com.example.diet.week_item.dto.WeekItem;

import java.util.ArrayList;
import java.util.List;

public class MealDetailDataAdapter extends RecyclerView.Adapter<MealDetailViewHolder> {
    private Context context;
    private ArrayList<FoodDetail> foods;

    private OnItemClickListener2 listener;

    public MealDetailDataAdapter(Context context, ArrayList<FoodDetail> foods, OnItemClickListener2 listener) {
        this.context = context;
        this.foods = foods;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MealDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_detail_item, parent, false);
        return new MealDetailViewHolder(view, listener, foods);
    }

    @Override
    public void onBindViewHolder(@NonNull MealDetailViewHolder holder, int position) {
        FoodDetail food = foods.get(position);
        holder.foodName.setText(food.getName());
        double scaledAmount = Math.round(food.getAmount() * 100.0) / 100.0;

        String amount = String.valueOf(scaledAmount) +" g" ;
        holder.size.setText(amount);
        // Load the image using Glide or any other image loading library
        Glide.with(context)
                .load(food.getIcon())
                .into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
