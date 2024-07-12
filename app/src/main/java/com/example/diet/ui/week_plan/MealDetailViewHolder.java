package com.example.diet.ui.week_plan;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;
import com.example.diet.food_detail.dto.FoodDetail;
import com.example.diet.ui.meal_info.OnItemClickListener;

import java.util.ArrayList;

public class MealDetailViewHolder extends RecyclerView.ViewHolder {
    TextView foodName;
    ImageView foodImage;
    TextView size;

    public MealDetailViewHolder(@NonNull View itemView, OnItemClickListener2 listener, ArrayList<FoodDetail> foodList) {
        super(itemView);
        foodName = itemView.findViewById(R.id.food_name_week_plan);
        foodImage = itemView.findViewById(R.id.food_image_week_plan);
        size = itemView.findViewById(R.id.size_week_plan);

        itemView.setOnClickListener(v -> {
            Log.d("MealDetailViewHolder", "Clicked");
            if (listener != null) {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Log.d("MealDetailViewHolder", "Food: " + foodList.get(position).get_id());
                    listener.onItemClick(foodList.get(position).get_id());
                }
            }
        });
    }
}
