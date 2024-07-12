package com.example.diet.goal.dto;

public class GoalResponse {
    private String id;
    private String goalName;
    private int sign;
    private boolean isActive;

    public GoalResponse(String id, String goalName, int sign, boolean isActive) {
        this.id = id;
        this.goalName = goalName;
        this.sign = sign;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
