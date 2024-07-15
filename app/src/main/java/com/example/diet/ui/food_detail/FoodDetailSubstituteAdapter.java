package com.example.diet.ui.food_detail;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diet.R;
import com.example.diet.food_detail.dto.FoodDetail;

import java.util.List;

public class FoodDetailSubstituteAdapter extends RecyclerView.Adapter<FoodDetailSubstituteAdapter.ViewHolder>{


    List<FoodDetail> foodDetails;
    Context context;


    public FoodDetailSubstituteAdapter(List<FoodDetail> foodDetails, Context context) {
        this.foodDetails = foodDetails;
        this.context = context;
    }


    @NonNull
    @Override
    public FoodDetailSubstituteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_detail_substitute,parent,false);


        FoodDetailSubstituteAdapter.ViewHolder viewHolder = new FoodDetailSubstituteAdapter.ViewHolder(view);


        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull FoodDetailSubstituteAdapter.ViewHolder holder, int position) {
        FoodDetail foodDetail = foodDetails.get(position);


        // Set the foodId as a tag on the update button
        holder.updateButton.setTag(foodDetail.getFood().get_id());


        holder.foodDetailName.setText(foodDetail.getFood().getFoodName());
        Glide.with(context)
                .load(foodDetail.getIcon())
                .into(holder.image);


        // Set touch listener
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Show the overlay and button
                        if(holder.overlayView.getVisibility() == View.GONE){
                            holder.overlayView.setVisibility(View.VISIBLE);
                            holder.updateButton.setVisibility(View.VISIBLE);
                        }else{
                            holder.overlayView.setVisibility(View.GONE);
                            holder.updateButton.setVisibility(View.GONE);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                }
                return true;
            }
        });


        // Set click listener for the update button
        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodId = (String) v.getTag();
                updateSubstitute(foodId);
            }
        });
    }


    @Override
    public int getItemCount() {
        return foodDetails.size();
    }


    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView foodDetailName;
        View overlayView;
        Button updateButton;


        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.substitute_image);
            foodDetailName = view.findViewById(R.id.substitute_name);
            overlayView = view.findViewById(R.id.overlay_view);
            updateButton = view.findViewById(R.id.update_button);
        }
    }


    private void updateSubstitute(String foodId){
        Log.d("Update", "Updating food with ID: " + foodId);
    }
}
