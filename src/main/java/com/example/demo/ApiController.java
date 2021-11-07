package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

@RestController
public class ApiController {

    ArrayList<Theme> themes = new ArrayList<>();

    @PutMapping("themes")
    public void addTheme(@RequestBody String theme) {
        themes.add(new Theme(theme));
    }

    @DeleteMapping("themes/{index}")
    public String deleteTheme(@PathVariable("index") int index) {
        if (index < 0 || index > themes.size() - 1)
            return "null";
        themes.remove(index);
        return "";
    }

    @GetMapping("themes")
    public String getThemes(@RequestParam(required = false) String sortWay) {
        ArrayList<Theme> sortedThemes = new ArrayList<>(themes);
        if (sortWay != null) {
            switch (sortWay) {
                case "name":
                    sortedThemes.sort(Comparator.comparing(Theme::getName));
                    break;
                case "updatedDate":
                    sortedThemes.sort(Comparator.comparing(Theme::getUpdatedDate));
                    Collections.reverse(sortedThemes);
                    break;
                default:
                    sortedThemes.sort(Comparator.comparing(Theme::getCreatedDate));
                    Collections.reverse(sortedThemes);
                    break;
            }
        }

        ArrayList<String> themesStrings = new ArrayList<>();
        for (Theme theme : sortedThemes) {
            themesStrings.add(theme.toString());
        }
        return String.join("\n", themesStrings);
    }

    @PostMapping("themes/{index}")
    public String changeTheme(@PathVariable("index") int index, @RequestBody String theme) {
        if (index < 0 || index > themes.size() - 1)
            return "null";
        themes.set(index, new Theme(theme));
        return "";
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
        if (index < 0 || index > themes.size() - 1)
            return "null";
        return themes.get(index).toString();
    }

    @PutMapping("comments/{userName}")
    public String addComment(@PathVariable("userName") String userName,@RequestBody Unpack data) {
        String themeName = data.theme;
        String comment = data.comment;
        boolean flag = false;
        for (Theme theme : themes) {
            if (theme.name.equals(themeName)) {
                theme.addComment(userName, comment);
                flag = true;
            }
        }
        if (!flag)
            return "null";
        return "";
    }

    @DeleteMapping("comments/{userName}")
    public String deleteComment(@PathVariable("userName") String userName, @RequestBody String themeName) {
        boolean flag = false;
        for (Theme theme : themes) {
            if (theme.name.equals(themeName)) {
                theme.deleteComment(userName);
            }
        }
        if (!flag)
            return "null";
        return "";
    }

    @PostMapping("comments/{userName}")
    public String changeComment(@PathVariable("userName") String userName, @RequestBody Unpack data) {
        boolean flag = false;
        String themeName = data.theme;
        String comment = data.comment;
        for (Theme theme : themes) {
            if (theme.name.equals(themeName)) {
                theme.changeComment(userName, comment);
                flag = true;
            }
        }

        if (!flag)
            return "null";
        return "";
    }

    @GetMapping("commentsByTheme")
    public String getCommentsByTheme(@RequestBody String themeName, @RequestParam(required = false) String sortWay) {
        for (Theme theme : themes) {
            if (theme.name.equals(themeName)) {
                ArrayList<Comment> sortedComments = new ArrayList<>(theme.comments);
                if (sortWay != null) {
                    switch (sortWay) {
                        case "text":
                            sortedComments.sort(Comparator.comparing(Comment::getText));
                            break;
                        case "updatedDate":
                            sortedComments.sort(Comparator.comparing(Comment::getUpdatedDate));
                            Collections.reverse(sortedComments);
                            break;
                        default:
                            sortedComments.sort(Comparator.comparing(Comment::getCreatedDate));
                            Collections.reverse(sortedComments);
                            break;
                    }
                }
                ArrayList<String> commentsStrings = new ArrayList<>();
                for (Comment comment : sortedComments) {
                    commentsStrings.add(comment.toString());
                }
                return String.join("\n", commentsStrings);
            }
        }
        return "null";
    }

    @GetMapping("commentsByUser/{userName}")
    public String getCommentsByUser(@PathVariable("userName") String userName, @RequestParam(required = false) String sortWay) {
        ArrayList<Comment> userComments = new ArrayList<>();
        for (Theme theme : themes) {
            for (Comment comment : theme.comments) {
                if (comment.user.equals(userName)) {
                    userComments.add(comment);
                }
            }
        }
        if (userComments.isEmpty())
            return "null";

        ArrayList<Comment> sortedComments = new ArrayList<>(userComments);
        if (sortWay != null) {
            switch (sortWay) {
                case "text":
                    sortedComments.sort(Comparator.comparing(Comment::getText));
                    break;
                case "updatedDate":
                    sortedComments.sort(Comparator.comparing(Comment::getUpdatedDate));
                    Collections.reverse(sortedComments);
                    break;
                default:
                    sortedComments.sort(Comparator.comparing(Comment::getCreatedDate));
                    Collections.reverse(sortedComments);
                    break;
            }
        }
        ArrayList<String> userCommentsText = new ArrayList<>();
        for (int i = 0; i < sortedComments.size(); i++) {
            userCommentsText.add(sortedComments.get(i).toString());
        }
        return String.join("\n", userCommentsText);
    }

    @DeleteMapping("comments/all/{userName}")
    public String deleteAllCommentsByUser(@PathVariable("userName") String userName) {
        boolean flag = false;
        for (Theme theme : themes) {
            this.deleteComment(userName, theme.name);
            flag = true;
        }

        if (!flag)
            return "null";

        return "";
    }




}