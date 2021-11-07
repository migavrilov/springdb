package com.example.demo;

import java.time.LocalDateTime;

public class Comment {
    public String text;
    public String user;
    public LocalDateTime createdDate;
    public LocalDateTime updatedDate;

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    Comment(String user, String text) {
        this.text = text;
        this.user = user;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = this.createdDate;
    }

    public Comment update(String newText) {
        this.text = newText;
        this.updatedDate = LocalDateTime.now();
        return this;
    }

    @Override
    public String toString() {
        return "'" + text + "' by " + user + ". Updated " + updatedDate.toString() + ". Created " + createdDate.toString();
    }
}
