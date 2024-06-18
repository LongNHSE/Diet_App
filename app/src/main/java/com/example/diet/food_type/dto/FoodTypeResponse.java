package com.example.diet.food_type.dto;

import java.util.List;

public class FoodTypeResponse {
    private List<food_type> data;
    private String message;

    public List<food_type> getData() {
        return data;
    }

    public void setData(List<food_type> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FoodTypeResponse{" +
                "data=" + data +

                ", message='" + message + '\'' +
                '}';
    }
}
