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

public class FoodDetailSubstituteAdapter extends RecyclerView.Adapter<FoodDetailSubstituteAdapter.ViewHolder> {

    private List<FoodDetail> foodDetails;
    private Context context;
    private OnSubstituteUpdateListener listener;

    public FoodDetailSubstituteAdapter(List<FoodDetail> foodDetails, Context context, OnSubstituteUpdateListener listener) {
        this.foodDetails = foodDetails;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_detail_substitute, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDetail foodDetail = foodDetails.get(position);

        // Set the foodId as a tag on the update button
        holder.updateButton.setTag(foodDetail.getFood().get_id());

        holder.foodDetailName.setText(foodDetail.getFood().getFoodName());
        Glide.with(context)
                .load(foodDetail.getIcon())
                .into(holder.image);

        // Set touch listener
        holder.itemView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // Toggle overlay and button visibility
                int newVisibility = holder.overlayView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE;
                holder.overlayView.setVisibility(newVisibility);
                holder.updateButton.setVisibility(newVisibility);
            }
            return true;
        });

        // Set click listener for the update button
        holder.updateButton.setOnClickListener(v -> {
            String foodId = (String) v.getTag();
            updateSubstitute(foodId);
        });
    }

    @Override
    public int getItemCount() {
        return foodDetails.size();
    }

    // ViewHolder class
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

    // Method to update substitute
    private void updateSubstitute(String foodId) {
        Log.d("FoodDetailSubstituteAdapter", "Updating food with ID: " + foodId);
        if (listener != null) {
            listener.onSubstituteUpdate(foodId);
        }
    }

    // Interface to handle substitute update
    public interface OnSubstituteUpdateListener {
        void onSubstituteUpdate(String foodId);
    }
}
