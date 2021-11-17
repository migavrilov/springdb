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

    ArrayList<User> users = new ArrayList<>();

    @PutMapping("users")
    public void addUser(@RequestBody NewUser newUser) {
        String rp = newUser.getRepassword();
        if (!(rp.equals(newUser.getPassword()))) {
            throw new NotSamePasswordException();
        }
        if (!(newUser.getUsername().matches("[A-Za-z]+"))) {
            throw new BadLettersException();
        }
        if (this.users.size() != 0) {
            for (int i = 0; i < this.users.size(); i++) {
                if (this.users.get(i).getUsername().equals(newUser.getUsername())) {
                    throw new ThisUserAlreadyExistsException();
                }
            }
        }

        User userToAdd = new User(newUser.getUsername(), newUser.getPassword(), newUser.getAge());
        users.add(userToAdd);
    }

    @PostMapping("users/{username}")
    public String getUser(@PathVariable("username") String username) {
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(username)) {
                return this.users.get(i).toString();
            }
        }

        throw new UserNotFoundException();
    }

    @DeleteMapping("users/{usernameToTakeAction}/{username}")
    public void deleteUser(@PathVariable("username") String username, @PathVariable("usernameToTakeAction") String usernameToTakeAction) {
        if (!(usernameToTakeAction.startsWith("admin"))) {
            throw new NotAllowedException();
        }
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(username)) {
                this.users.remove(i);
                return;
            }
        }
        throw new UserNotFoundException();
    }

    @PutMapping("users/{usernameToTakeAction}/{usernameToUpdate}/{newPassword}")
    public void updatePassword(@PathVariable("usernameToTakeAction") String usernameToTakeAction, @PathVariable("usernameToUpdate") String usernameToUpdate, @PathVariable("newPassword") String newPassword) {
        if (!(usernameToTakeAction.startsWith("update"))) {
            throw new NotAllowedException();
        }
        if (!(usernameToUpdate.matches("[A-Za-z]+"))) {
            throw new BadLettersException();
        }
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(usernameToUpdate)) {
                this.users.get(i).setPassword(newPassword);
                return;
            }
        }
        throw new UserNotFoundException();

    }

    @GetMapping("users/{age}")
    public String getUsers(@PathVariable("age") int age, @RequestParam(required = false) String direction) {
        String toReturn = "";
        if (direction != null && direction.equals("up")) {
            for (int i = this.users.size() - 1; i >= 0; i--) {
                if  (this.users.get(i).getAge() >= age - 5 && this.users.get(i).getAge() <= age +5) {
                    toReturn = toReturn.concat(this.users.get(i).toString());
                    toReturn = toReturn.concat("\n");
                }
            }
        } else {
            for (int i = 0; i < this.users.size(); i++) {
                if (this.users.get(i).getAge() >= age - 5 && this.users.get(i).getAge() <= age + 5) {
                    toReturn = toReturn.concat(this.users.get(i).toString());
                    toReturn = toReturn.concat("\n");
                }
            }
        }
        return toReturn;
    }





}