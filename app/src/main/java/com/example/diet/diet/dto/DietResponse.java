package com.example.diet.diet.dto;

import java.util.Date;

public class DietResponse {
    private String userId;
    private String preferenceId;
    private String activityLevelId;
    private String goalId;
    private int height;
    private int weight;
    private int duration;
    private int main;
    private int side;
    private int session;
    private int amountOfChange;
    private boolean isActive;
    private String status;
    private String _id;
    private Date createdAt;
    private Date updatedAt;
    private int __v;

    // Getters and Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(String preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getActivityLevelId() {
        return activityLevelId;
    }

    public void setActivityLevelId(String activityLevelId) {
        this.activityLevelId = activityLevelId;
    }

    public String getGoalId() {
        return goalId;
    }

    public void setGoalId(String goalId) {
        this.goalId = goalId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public int getAmountOfChange() {
        return amountOfChange;
    }

    public void setAmountOfChange(int amountOfChange) {
        this.amountOfChange = amountOfChange;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getVersion() {
        return __v;
    }

    public void setVersion(int __v) {
        this.__v = __v;
    }
}
