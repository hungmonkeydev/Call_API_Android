package com.example.callapipart1.model;

public class PostResponse {
    private String message;
    private Post data;

    public String getMessage() {
        return message;
    }

    public Post getData() {
        return data;
    }

    @Override
    public String toString() {
        return "{message:'" + message + "', data:" + data + "}";
    }
}
