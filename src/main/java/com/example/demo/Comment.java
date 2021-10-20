package com.example.demo;

public class Comment {
    public String text;
    public String user;

    Comment(String user, String text) {
        this.text = text;
        this.user = user;
    }

    @Override
    public String toString() {
        return "'" + text + "' by " + user;

    }
}
