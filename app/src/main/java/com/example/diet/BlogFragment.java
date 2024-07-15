package com.example.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.blog.dto.Blog;
import com.example.diet.blog.dto.BlogResponse;
import com.example.diet.blog.service.BlogService;
import com.example.diet.util.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogFragment extends Fragment {
    private RecyclerView recyclerViewBlog;
    private List<Blog> blogList;
    private BlogAdapter blogAdapter;

    public BlogFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       return inflater.inflate(R.layout.activity_blog,container,false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewBlog =  requireActivity().findViewById(R.id.recyclerViewBlog);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( requireActivity());
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
                    Toast.makeText( requireActivity(), "Failed to fetch blogs", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BlogResponse<List<Blog>>> call, Throwable t) {
                Toast.makeText( requireActivity(), "Failed to fetch blogs: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
