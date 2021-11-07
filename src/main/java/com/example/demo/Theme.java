package com.example.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Theme {
    public String name;
    public ArrayList<Comment> comments = new ArrayList<>();
    public LocalDateTime createdDate;
    public LocalDateTime updatedDate;

    Theme(String themeName) {
        name = themeName;
        createdDate = LocalDateTime.now();
        updatedDate = createdDate;
    }

    @Override
    public String toString() {
        return name + ". Updated " + updatedDate.toString() + ". Created " + createdDate.toString();
    }

    public void addComment(String userName, String comment) {
        this.updatedDate = LocalDateTime.now();
        comments.add(new Comment(userName, comment));
    }

    public String changeComment(String user, String newComment) {
        boolean flag = false;
        for (int i = 0; i < this.comments.size(); i++) {
            if (this.comments.get(i).user.equals(user)) {
                this.comments.get(i).text = newComment;
                LocalDateTime updated = LocalDateTime.now();
                this.comments.get(i).updatedDate = updated;
                this.updatedDate = updated;
                flag = true;
                break;
            }
        }

        if (!flag) {
            return "null";
        }
        return "";
    }

    public String deleteComment(String user) {
        boolean flag = false;
        for (int i = 0; i < this.comments.size(); i++) {
            if (this.comments.get(i).user.equals(user)) {
                this.comments.remove(i);
                LocalDateTime updated = LocalDateTime.now();
                this.updatedDate = updated;
                flag = true;
                break;
            }
        }

        if (!flag) {
            return "null";
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}
