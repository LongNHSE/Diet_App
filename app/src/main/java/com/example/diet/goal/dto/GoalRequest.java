package com.example.diet.goal.dto;

public class GoalRequest {
    private String goalName;
    private int sign;

    public GoalRequest(String goalName, int sign) {
        this.goalName = goalName;
        this.sign = sign;
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
}
