package com.example.diet.week_item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diet.R;
import com.example.diet.preference.dto.PreferenceResponse;

import java.util.List;

public class CircularPreferenceSliderAdapter extends RecyclerView.Adapter<CircularPreferenceSliderAdapter.PreferenceViewHolder> {

    private Context context;
    private List<PreferenceResponse> preferenceList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CircularPreferenceSliderAdapter(Context context, List<PreferenceResponse> preferenceList) {
        this.context = context;
        this.preferenceList = preferenceList;
    }

    @NonNull
    @Override
    public PreferenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_preference_slider, parent, false);
        return new PreferenceViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PreferenceViewHolder holder, int position) {
        int actualPosition = position % preferenceList.size();
        PreferenceResponse preference = preferenceList.get(actualPosition);
        int imageResId = getImageResId(preference.getName());

        Glide.with(context)
                .load(imageResId)
                .override(600, 600)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE; // Return a large number to simulate circular behavior
    }

    public static class PreferenceViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public PreferenceViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.preferenceImageView);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    private int getImageResId(String name) {
        switch (name.toLowerCase()) {
            case "vegan":
                return R.drawable.vegan;
            case "no preference":
                return R.drawable.no_preference;
            case "asian & pacific":
                return R.drawable.asia;
            case "european & north american":
                return R.drawable.euro;
            case "mediterranean & hispanic":
                return R.drawable.mediterranean;
            default:
                return R.drawable.placeholder_image;
        }
    }
}
