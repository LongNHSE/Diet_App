package com.example.diet.exercise.dto.service.dto;

public class Exercise {
    private String id;
    private String exName;
    private int calorexp;
    private String icon;
    private String description;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public int getCalorexp() {
        return calorexp;
    }

    public void setCalorexp(int calorexp) {
        this.calorexp = calorexp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id='" + id + '\'' +
                ", exName='" + exName + '\'' +
                ", calorexp=" + calorexp +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}