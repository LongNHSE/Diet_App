package com.example.diet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.blog.dto.Blog;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.UserViewHolder> {

    private List<Blog> blogList;
    private Context context;

    public BlogAdapter(List<Blog> blogList) {
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_blog, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
        final Blog blog = blogList.get(position);
        if (blog != null) {
            String imageUrl = blog.getImageUrl();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_image)
                        .into(holder.imageView);
            } else {
                holder.imageView.setImageResource(R.drawable.placeholder_image); // Set placeholder if no image URL
            }
            holder.nameTextView.setText(blog.getName());
            holder.subtitleTextView.setText(blog.getSubtitle());
            holder.textViewRating.setText(String.valueOf(blog.getAverageRating()));
            holder.textViewReviews.setText(String.valueOf("/ "+ blog.getNumRating() + " reviews" ));

            // Handle click on blog item
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to BlogDetailActivity
                    Intent intent = new Intent(context, BlogDetailActivity.class);
                    intent.putExtra("blog_id", blog.getId()); // Pass blog id or necessary data
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView nameTextView;
        private final TextView subtitleTextView;
        private final TextView textViewRating;
        private final TextView textViewReviews;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            subtitleTextView = itemView.findViewById(R.id.subtitleTextView);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewReviews = itemView.findViewById(R.id.textViewReviews);
        }
    }
}

