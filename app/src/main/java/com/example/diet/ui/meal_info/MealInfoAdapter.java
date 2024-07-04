package com.example.diet.ui.meal_info;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diet.R;
import com.example.diet.food.dto.Food;
import com.example.diet.food_detail.dto.FoodDetail;

import java.util.ArrayList;

public class MealInfoAdapter extends RecyclerView.Adapter<MealInfoAdapter.MealInfoViewHolder> {
    Context context;
    ArrayList<FoodDetail> foodList;

    public MealInfoAdapter(Context context, ArrayList<FoodDetail> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public MealInfoAdapter.MealInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.meal_info_card, parent, false);
        return new MealInfoAdapter.MealInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealInfoAdapter.MealInfoViewHolder holder, int position) {
        FoodDetail food = foodList.get(position);
        Log.d("FoodDetail", "Food: " + foodList.size());


        holder.foodName.setText( food.getFood().getFoodName());
        holder.description.setText(food.getDescription());
        Glide.with(context)
                .load(food.getIcon())
                .placeholder(R.drawable.lunch) // Add a placeholder if you have one
                .error(R.drawable.breakfast) // Add an error image if you have one
                .into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        Log.d("FoodDetail", "Food: " + foodList.size());
        return foodList.size();
    }

    public static class MealInfoViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName, description;

        public MealInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.food_image_meal_info);
            foodName = itemView.findViewById(R.id.food_name_meal_info);
            description = itemView.findViewById(R.id.food_description_meal_info);
        }
    }
}
