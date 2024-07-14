package com.example.diet.activity_level.dto;

public class ActivityLevelRequest {
    private String levelName;
    private double level;
    private boolean isActive;

    // Getters and Setters
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}