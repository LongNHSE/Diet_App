package com.example.diet.bmi.dto;

public class BMIResponse {
    private String userId;
    private int weight;
    private int height;
    private double bmi;

    public BMIResponse(String userId, int weight, int height, double bmi) {
        this.userId = userId;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
}
