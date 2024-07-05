package com.example.diet.diet.dto;

import com.example.diet.day.dto.Day;

import java.util.Date;

public class diet {
    private String _id;
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
    private Date createdAt;
    private Date updatedAt;
    private Day day;
    // Getters and Setters

    public diet(String _id, String userId, String preferenceId, String activityLevelId, String goalId, int height, int weight, int duration, int main, int side, int session, int amountOfChange, boolean isActive, String status, Date createdAt, Date updatedAt, Day day) {
        this._id = _id;
        this.userId = userId;
        this.preferenceId = preferenceId;
        this.activityLevelId = activityLevelId;
        this.goalId = goalId;
        this.height = height;
        this.weight = weight;
        this.duration = duration;
        this.main = main;
        this.side = side;
        this.session = session;
        this.amountOfChange = amountOfChange;
        this.isActive = isActive;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.day = day;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

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

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "DietDTO{" +
                "id='" + _id + '\'' +
                ", userId='" + userId + '\'' +
                ", preferenceId='" + preferenceId + '\'' +
                ", activityLevelId='" + activityLevelId + '\'' +
                ", goalId='" + goalId + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", duration=" + duration +
                ", main=" + main +
                ", side=" + side +
                ", session=" + session +
                ", amountOfChange=" + amountOfChange +
                ", isActive=" + isActive +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", Day=" + day +
                '}';
    }
}