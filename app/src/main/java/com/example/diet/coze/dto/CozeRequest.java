package com.example.diet.coze.dto;

import com.google.gson.annotations.SerializedName;

public class CozeRequest {
    @SerializedName("conversation_id")
    private String conversationId;


    @SerializedName("bot_id")
    private String botId;


    @SerializedName("user")
    private String user;


    @SerializedName("query")
    private String query;


    @SerializedName("stream")
    private boolean stream;


    // Constructor, Getters, and Setters


    public CozeRequest(String conversationId, String botId, String user, String query, boolean stream) {
        this.conversationId = conversationId;
        this.botId = botId;
        this.user = user;
        this.query = query;
        this.stream = stream;
    }


    public String getConversationId() {
        return conversationId;
    }


    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }


    public String getBotId() {
        return botId;
    }


    public void setBotId(String botId) {
        this.botId = botId;
    }


    public String getUser() {
        return user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public String getQuery() {
        return query;
    }


    public void setQuery(String query) {
        this.query = query;
    }


    public boolean isStream() {
        return stream;
    }


    public void setStream(boolean stream) {
        this.stream = stream;
    }

}
