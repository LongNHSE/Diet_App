package com.example.diet.blog.service;

import com.example.diet.blog.dto.Blog;
import com.example.diet.blog.dto.BlogResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BlogService {
    @GET("blog")
    Call<BlogResponse<List<Blog>>> getBlogs();
    @GET("blog/{id}")
    Call<BlogResponse<Blog>> getDetailBlog(@Path("id") String blogId);
}
