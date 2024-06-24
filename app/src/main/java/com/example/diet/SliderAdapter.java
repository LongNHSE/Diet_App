package com.example.diet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diet.SlideItem;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SlideItem> slideItems;

    public SliderAdapter(List<SlideItem> slideItems) {
        this.slideItems = slideItems;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.bind(slideItems.get(position));
    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleView;
        private TextView subtitleView;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleView = itemView.findViewById(R.id.titleView);
            subtitleView = itemView.findViewById(R.id.subtitleView);
        }

        void bind(SlideItem slideItem) {
            imageView.setImageResource(slideItem.getImageResId());
            titleView.setText(slideItem.getTitle());
            subtitleView.setText(slideItem.getSubtitle());
        }
    }
}