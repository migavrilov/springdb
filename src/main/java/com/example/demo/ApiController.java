package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ApiController {

    ArrayList<String> messages = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    @GetMapping("messages")
    public String getMessages() {
        return String.join(", ", messages);
    }

    @PostMapping("messages")
    public String createMessage(@RequestBody String message) {
        messages.add(message);
        return message + " was created.";
    }

    @GetMapping("messages/{index}")
    public String getMessage(@PathVariable("index") String index) {
        try {
            int i = Integer.parseInt(index);
            if (i < messages.size()) {
                return messages.get(i);
            } else {
                return "There is no message with requested index.";
            }
        } catch (NumberFormatException ex) {
            return "Index must be an integer.";
        }
    }

    @DeleteMapping("messages/{index}")
    public String deleteMessage(@PathVariable("index") String index) {
        try {
            int i = Integer.parseInt(index);
            if (i < messages.size()) {
                messages.remove( (int) i);
                return "Message was deleted successfully.";
            } else {
                return "There is no message with requested index.";
            }
        } catch (NumberFormatException ex) {
            return "Index must be an integer.";
        }
    }

    @PutMapping("messages/{index}")
    public String updateMessage(@PathVariable("index") String index, @RequestBody String message) {
        try {
            int i = Integer.parseInt(index);
            if (i < messages.size()) {
                messages.remove(i);
                messages.add(i, message);
                return "Message was changed successfully.";
            } else {
                return "There is no message with requested index.";
            }
        } catch (NumberFormatException ex) {
            return "Index must be an integer.";
        }
    }

    @PutMapping("users")
    public void addUser(@RequestBody User userToAdd) {
        users.add(userToAdd);
    }

    @DeleteMapping("users/{index}")
    public void deleteUser(@PathVariable("index") int index) {
        users.remove((int) index);
    }

    @GetMapping("users/{index}")
    public String getUser(@PathVariable("index") int index) {
        return users.get(index).toString();
    }

    @GetMapping("users")
    public String getUsers() {
        ArrayList<String> usersStrings = new ArrayList<>();
        for (User user : users) {
            usersStrings.add(user.toString());
        }
        return String.join("\n", usersStrings);
    }

    @PutMapping("users/{index}/{age}")
    public void changeAge(@PathVariable("index") int index, @PathVariable("age") int age) {
        users.get(index).setAge(age);
    }






}