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
import com.example.diet.food_detail.dto.FoodDetail;

import java.util.ArrayList;

public class MealInfoAdapter extends RecyclerView.Adapter<MealInfoAdapter.MealInfoViewHolder> {
    private Context context;
    private ArrayList<FoodDetail> foodList;
    private OnItemClickListener listener;

    public MealInfoAdapter(Context context, ArrayList<FoodDetail> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MealInfoAdapter.MealInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.meal_info_card, parent, false);
        return new MealInfoAdapter.MealInfoViewHolder(view, listener, foodList);
    }

    @Override
    public void onBindViewHolder(@NonNull MealInfoAdapter.MealInfoViewHolder holder, int position) {
        FoodDetail food = foodList.get(position);

        holder.foodName.setText(food.getFood().getFoodName());
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



        public MealInfoViewHolder(@NonNull View itemView, OnItemClickListener listener,ArrayList<FoodDetail> foodList) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.food_image_meal_info);
            foodName = itemView.findViewById(R.id.food_name_meal_info);
            description = itemView.findViewById(R.id.food_description_meal_info);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(foodList.get(position).get_id());
                    }
                }
            });
        }
    }
}
