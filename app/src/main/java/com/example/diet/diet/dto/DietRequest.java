package com.example.diet.diet.dto;

public class DietRequest {
    private String activityLevelId;
    private String preferenceId;
    private String goalId;
    private int duration;
    private int main;
    private int side;
    private int session;
    private int amountOfChange;

    public DietRequest(String activityLevelId, String preferenceId, String goalId, int duration, int main, int side, int session, int amountOfChange) {
        this.activityLevelId = activityLevelId;
        this.preferenceId = preferenceId;
        this.goalId = goalId;
        this.duration = duration;
        this.main = main;
        this.side = side;
        this.session = session;
        this.amountOfChange = amountOfChange;
    }

    public String getActivityLevelId() {
        return activityLevelId;
    }

    public void setActivityLevelId(String activityLevelId) {
        this.activityLevelId = activityLevelId;
    }

    public String getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(String preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getGoalId() {
        return goalId;
    }

    public void setGoalId(String goalId) {
        this.goalId = goalId;
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

    @Override
    public String toString() {
        return "DietRequest{" +
                "activityLevelId='" + activityLevelId + '\'' +
                ", preferenceId='" + preferenceId + '\'' +
                ", goalId='" + goalId + '\'' +
                ", duration=" + duration +
                ", main=" + main +
                ", side=" + side +
                ", session=" + session +
                ", amountOfChange=" + amountOfChange +
                '}';
    }
}
