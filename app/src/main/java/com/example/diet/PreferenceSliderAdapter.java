package com.example.diet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.preference.dto.PreferenceResponse;

import java.util.List;

public class PreferenceSliderAdapter extends RecyclerView.Adapter<PreferenceSliderAdapter.PreferenceViewHolder> {

    private Context context;
    private List<PreferenceResponse> preferenceList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }



    @NonNull
    @Override
    public PreferenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_preference_slider, parent, false);
        return new PreferenceViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PreferenceViewHolder holder, int position) {
        PreferenceResponse preference = preferenceList.get(position);
        int imageResId = getImageResId(preference.getName());
        holder.imageView.setImageResource(imageResId);
    }

    @Override
    public int getItemCount() {
        return preferenceList.size();
    }

    public static class PreferenceViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public PreferenceViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.preferenceImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
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
