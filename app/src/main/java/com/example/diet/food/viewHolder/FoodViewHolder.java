package com.example.diet.food.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;

public class FoodViewHolder extends RecyclerView.ViewHolder {
    public TextView foodName;

    public TextView size;

    public ImageView icon;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        foodName = itemView.findViewById(R.id.foodName);
        icon = itemView.findViewById(R.id.foodImage);
        size = itemView.findViewById(R.id.size);
    }
}
