package com.example.diet.coze.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CozeResponse {
    @SerializedName("messages")
    private List<Message> messages;


    @SerializedName("conversation_id")
    private String conversationId;


    @SerializedName("code")
    private int code;


    @SerializedName("msg")
    private String msg;


    // Getters and Setters


    public List<Message> getMessages() {
        return messages;
    }


    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }


    public String getConversationId() {
        return conversationId;
    }


    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }


    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }

}
