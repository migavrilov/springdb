package com.example.demo;

import java.util.ArrayList;

public class Theme {
    public String name;
    public ArrayList<Comment> comments = new ArrayList<>();

    Theme(String themeName) {
        name = themeName;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addComment(String userName, String comment) {
        comments.add(new Comment(userName, comment));
    }
}
