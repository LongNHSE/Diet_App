package com.example.diet;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.blog.dto.Blog;
import com.example.diet.blog.dto.BlogResponse;
import com.example.diet.blog.service.BlogService;
import com.example.diet.response.ResponseDTO;
import com.example.diet.util.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogActivity extends AppCompatActivity {
    private RecyclerView recyclerViewBlog;
    private List<Blog> blogList;
    private BlogAdapter blogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        recyclerViewBlog = findViewById(R.id.recyclerViewBlog);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewBlog.setLayoutManager(linearLayoutManager);

        blogList = new ArrayList<>();
        blogAdapter = new BlogAdapter(blogList);
        recyclerViewBlog.setAdapter(blogAdapter);

        // Fetch blogs from API
        getApiBlogs();
    }

    private void getApiBlogs() {
        BlogService blogService = RetrofitClient.getClient(null).create(BlogService.class);
        Call<BlogResponse<List<Blog>>> call = blogService.getBlogs();

        call.enqueue(new Callback<BlogResponse<List<Blog>>>() {
            @Override
            public void onResponse(Call<BlogResponse<List<Blog>>> call, Response<BlogResponse<List<Blog>>> response) {
                if (response.isSuccessful()) {
                    BlogResponse<List<Blog>> blogResponse = response.body();
                    if (blogResponse != null) {
                        blogList.clear(); // Clear existing data
                        blogList.addAll(blogResponse.getData());
                        blogAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(BlogActivity.this, "Failed to fetch blogs", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BlogResponse<List<Blog>>> call, Throwable t) {
                Toast.makeText(BlogActivity.this, "Failed to fetch blogs: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
