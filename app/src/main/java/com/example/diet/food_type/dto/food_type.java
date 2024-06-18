package com.example.diet.food_type.dto;

public class food_type {
    private String _id;
    private String name;

    private boolean isActive;


    public food_type() {
    }

    public food_type(String _id, String name, boolean isActive) {
        this._id = _id;
        this.name = name;
        this.isActive = isActive;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "food_type{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
