package com.example.diet.blog.dto;

import android.net.Uri;

import java.util.List;

public class Blog {
    private String _id;

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    private String name;
    private String subtitle;
    private double averageRating;
    private int numRating;
    private List<String> ingredients;
    private List<String> directions;
    private List<String> images;
    private String createDate;
    private String status;
    private String createdDate;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumRating() {
        return numRating;
    }

    public void setNumRating(int numRating) {
        this.numRating = numRating;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    public String getImageUrl() {
        if (images != null && !images.isEmpty()) {
            return images.get(0); // Assuming the first image URL is sufficient for display
        }
        return null; // Return null or default image URL if no images available
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
