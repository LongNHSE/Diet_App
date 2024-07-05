package com.example.diet.ui.week_plan;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.R;

public class DayPlanViewHolder extends RecyclerView.ViewHolder {
    TextView typeName;
    TextView typeName2;
    TextView typeName3;
    ImageView icon;

    public DayPlanViewHolder(@NonNull View itemView) {
        super(itemView);
        typeName = itemView.findViewById(R.id.activity);
        typeName2 = itemView.findViewById(R.id.mealtypeName);
        typeName3 = itemView.findViewById(R.id.ingredients);
        icon = itemView.findViewById(R.id.img);

        // Set click listener for the itemView
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    // Notify the adapter of the click event
                    ((MealDayDataAdapter.OnItemClickListener) itemView.getContext()).onItemClick(String.valueOf(position));
                }
            }
        });
    }
}
