package com.example.diet.blog.dto;

public class BlogResponse<T> {
    private int statusCode;
    private String message;
    private T data; // Use T to handle different data types

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Getters and Setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
