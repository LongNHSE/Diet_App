package com.example.diet.ui.week_plan;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;
import com.example.diet.meal.dto.Meal;

import java.util.List;

public class DayPlanViewHolder extends RecyclerView.ViewHolder {
    public TextView typeName;
    public TextView typeName2;
    public TextView typeName3;
    public ImageView icon;
    public ImageView clickButton;



    public DayPlanViewHolder(@NonNull View itemView, final MealDayDataAdapter.OnItemClickListener listener, List<Meal> mealList) {
        super(itemView);

        typeName = itemView.findViewById(R.id.activity);
        typeName2 = itemView.findViewById(R.id.mealtypeName);
        typeName3 = itemView.findViewById(R.id.ingredients);
        icon = itemView.findViewById(R.id.img);
        clickButton = itemView.findViewById(R.id.search_3);

        // Set click listener for the whole item view
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(mealList.get(position).get_id());
                }
            }
        });

        // Set click listener for the clickButton ImageView
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(mealList.get(position).get_id());
                }
            }
        });
    }
}
