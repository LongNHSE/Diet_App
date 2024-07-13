package com.example.diet;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.blog.dto.Blog;
import com.example.diet.blog.dto.BlogResponse;
import com.example.diet.blog.service.BlogService;
import com.example.diet.util.RetrofitClient;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class BlogDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerViewIngredients, recyclerViewDirections;
    private ImageView imageViewBlog;
    private TextView textViewTitle, textViewRating;
    private String blogId;
    private LinearLayout linearLayoutStars;
    private TabLayout tabLayout;
    private ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);
        linearLayoutStars = findViewById(R.id.linearLayoutStars);
        recyclerViewIngredients = findViewById(R.id.recyclerViewIngredients);
        recyclerViewDirections = findViewById(R.id.recyclerViewDirections);
        imageViewBlog = findViewById(R.id.imageViewBlog);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewRating = findViewById(R.id.textViewRating);
        buttonBack = findViewById(R.id.buttonBack);

        tabLayout = findViewById(R.id.tabLayout);

        // Get blog ID from intent
        blogId = getIntent().getStringExtra("blog_id");

        if (blogId != null) {
            getBlogDetails(blogId);
        }

        // Setup tab layout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        recyclerViewIngredients.setVisibility(View.VISIBLE);
                        recyclerViewDirections.setVisibility(View.GONE);
                        break;
                    case 1:
                        recyclerViewIngredients.setVisibility(View.GONE);
                        recyclerViewDirections.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // Set up back button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getBlogDetails(String blogId) {
        BlogService blogService = RetrofitClient.getClient(null).create(BlogService.class);
        Call<BlogResponse<Blog>> call = blogService.getDetailBlog(blogId);

        call.enqueue(new Callback<BlogResponse<Blog>>() {
            @Override
            public void onResponse(Call<BlogResponse<Blog>> call, Response<BlogResponse<Blog>> response) {
                if (response.isSuccessful()) {
                    BlogResponse<Blog> blogResponse = response.body();
                    if (blogResponse != null) {
                        Blog blog = blogResponse.getData();
                        displayBlogDetails(blog);
                    }
                } else {
                    Toast.makeText(BlogDetailActivity.this, "Failed to fetch blog details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BlogResponse<Blog>> call, Throwable t) {
                Toast.makeText(BlogDetailActivity.this, "Failed to fetch blog details: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayBlogDetails(Blog blog) {
        textViewTitle.setText(blog.getName());
        textViewRating.setText(blog.getNumRating() + " reviews");

        if (blog.getImageUrl() != null && !blog.getImageUrl().isEmpty()) {
            Picasso.get().load(blog.getImageUrl()).into(imageViewBlog);
        } else {
            imageViewBlog.setImageResource(R.drawable.placeholder_image);
        }

        // Set star rating
        setStarRating(blog.getAverageRating());

        setupRecyclerView(recyclerViewIngredients, blog.getIngredients());
        setupRecyclerView(recyclerViewDirections, blog.getDirections());
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<String> items) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (recyclerView == recyclerViewIngredients) {
            IngredientAdapter adapter = new IngredientAdapter(items);
            recyclerView.setAdapter(adapter);
        } else if (recyclerView == recyclerViewDirections) {
            DirectionAdapter adapter = new DirectionAdapter(items);
            recyclerView.setAdapter(adapter);
        }
    }

    private void setStarRating(double averageRating) {
        int fullStars = (int) averageRating;
        boolean hasHalfStar = averageRating - fullStars >= 0.5;

        linearLayoutStars.removeAllViews();

        for (int i = 0; i < fullStars; i++) {
            ImageView star = new ImageView(this);
            star.setImageResource(R.drawable.ic_star_full);
            linearLayoutStars.addView(star);
        }

        if (hasHalfStar) {
            ImageView halfStar = new ImageView(this);
            halfStar.setImageResource(R.drawable.ic_star_half);
            linearLayoutStars.addView(halfStar);
        }

        for (int i = fullStars + (hasHalfStar ? 1 : 0); i < 5; i++) {
            ImageView emptyStar = new ImageView(this);
            emptyStar.setImageResource(R.drawable.ic_star_empty);
            linearLayoutStars.addView(emptyStar);
        }
    }
}
