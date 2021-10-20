package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
public class ApiController {

    ArrayList<Theme> themes = new ArrayList<>();

    @PutMapping("themes")
    public void addTheme(@RequestBody String theme) {
        themes.add(new Theme(theme));
    }

    @DeleteMapping("themes/{index}")
    public void deleteTheme(@PathVariable("index") int index) {
        themes.remove(index);
    }

    @GetMapping("themes")
    public String getThemes() {
        ArrayList<String> themesStrings = new ArrayList<>();
        for (Theme theme : themes) {
            themesStrings.add(theme.toString());
        }
        return String.join("\n", themesStrings);
    }

    @PostMapping("themes/{index}")
    public void changeTheme(@PathVariable("index") int index, @RequestBody String theme) {
        themes.set(index, new Theme(theme));
    }

    @GetMapping("themes/quantity")
    public String getThemesQuantity() {
        return String.valueOf(themes.size());
    }

    @DeleteMapping("themes/all")
    public void deleteAllThemes() {
        themes = new ArrayList<>();
    }

    @GetMapping("themes/{index}")
    public String getTheme(@PathVariable("index") int index) {
        return themes.get(index).toString();
    }

    @PutMapping("comments/{userName}")
    public void addComment(@PathVariable("userName") String userName,@RequestBody Unpack data) {
        String themeName = data.theme;
        String comment = data.comment;
        for (Theme theme : themes) {
            if (theme.name.equals(themeName)) {
                theme.addComment(userName, comment);
            }
        }
    }

    @DeleteMapping("comments/{userName}")
    public void deleteComment(@PathVariable("userName") String userName, @RequestBody String themeName) {
        for (Theme theme : themes) {
            if (theme.name.equals(themeName)) {
                for (int i = 0; i < theme.comments.size(); i++) {
                    if (theme.comments.get(i).user.equals(userName)) {
                        theme.comments.remove(i);
                    }
                }
            }
        }
    }

    @PostMapping("comments/{userName}")
    public void changeComment(@PathVariable("userName") String userName, @RequestBody Unpack data) {
        String themeName = data.theme;
        String comment = data.comment;
        for (Theme theme : themes) {
            if (theme.name.equals(themeName)) {
                int index = 0;
                for (int i = 0; i < theme.comments.size(); i++) {
                    if (theme.comments.get(i).user.equals(userName)) {
                        index = i;
                        break;
                    }
                }
                theme.comments.set(index, new Comment(theme.comments.get(index).user, comment));
            }
        }
    }

    @GetMapping("commentsByTheme")
    public String getCommentsByTheme(@RequestBody String themeName) {
        for (Theme theme : themes) {
            if (theme.name.equals(themeName)) {
                ArrayList<String> commentsStrings = new ArrayList<>();
                for (Comment comment : theme.comments) {
                    commentsStrings.add(comment.toString());
                }
                return String.join("\n", commentsStrings);
            }
        }
        return "";
    }

    @GetMapping("commentsByUser/{userName}")
    public String getCommentsByUser(@PathVariable("userName") String userName) {
        ArrayList<String> userComments = new ArrayList<>();
        for (Theme theme : themes) {
            for (Comment comment : theme.comments) {
                if (comment.user.equals(userName)) {
                    userComments.add(comment.text);
                }
            }
        }

        return String.join("\n", userComments);
    }

    @DeleteMapping("comments/all/{userName}")
    public void deleteAllCommentsByUser(@PathVariable("userName") String userName) {
        for (Theme theme : themes) {
            this.deleteComment(userName, theme.name);
        }
    }




}