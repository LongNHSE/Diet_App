package com.example.diet.response;

public class ResponseDTO<T>{
    private String message;

    int statusCode;
    private T data;

    public ResponseDTO(String message, T data, int statusCode) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }



    public ResponseDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "responseDTO{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
