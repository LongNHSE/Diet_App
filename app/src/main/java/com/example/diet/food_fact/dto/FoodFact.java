package com.example.diet.food_fact.dto;

public class FoodFact {
    private String _id;
    private String title;
    private String description;
    private String imageUrl;


    public String get_id() {
        return _id;
    }


    public void set_id(String _id) {
        this._id = _id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getImageUrl() {
        return imageUrl;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
