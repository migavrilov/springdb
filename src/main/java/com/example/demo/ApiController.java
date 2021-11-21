package com.example.demo;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.*;

@RestController
public class ApiController {
    private final UserRepository userRepository;

    public ApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(required = false) String age,
                               @RequestParam(required = false) String sortBy,
                               @RequestParam(required = false) String direction) {
        if (sortBy == null) {
            sortBy = "id";
        }
        if (age != null) {
            return userRepository.findAllByAge(Integer.parseInt(age), sortBy, direction);
        } else {
            if (direction.equals("up")) {
                return userRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
            } else {
                return userRepository.findAll(Sort.by(Sort.Direction.DESC, sortBy));
            }
        }

    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user, @RequestParam String rePassword) {
        if (rePassword != null && !(user.getPassword().equals(rePassword))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //password != rePassword
        }
        for (long i = 1; i < userRepository.findAll().size() + 1; i++) {
            if (user.getUsername().equals(userRepository.getById(i).getUsername())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT); //This user already exists
            }
        }
        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") long id) {
        Optional<User> userData =
                userRepository.findById(id);
        if (userData.isPresent()) {
            return userData.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); //User not found
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); //User not found
        }
    }

    @PutMapping("users")
    public void updateUserById(@RequestBody User user, @RequestParam String rePassword) {
        if (!(user.getPassword().equals(rePassword))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //password != rePassword
        }
        if (userRepository.findById(user.getId()).isPresent()) {
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); //User not found
        }
    }



//    @DeleteMapping("/tutorials")
//    public HttpStatus deleteAllTutorials() {
//        userRepository.deleteAll();
//        return HttpStatus.NO_CONTENT; }
//    @GetMapping("/tutorials/published")
//    public List<Tutorial> findByPublished() {
//        return userRepository.findByPublished(true);
//    }


//    @PutMapping("/tutorials/{id}")
//    public Tutorial updateTutorial(@PathVariable("id") long id,
//                                   @RequestBody Tutorial tutorial) {
//        Optional<Tutorial> tutorialData =
//                tutorialRepository.findById(id);
//        if (tutorialData.isPresent()) {
//            Tutorial _tutorial = tutorialData.get();
//            _tutorial.setTitle(tutorial.getTitle());
//            _tutorial.setDescription(tutorial.getDescription());
//            _tutorial.setPublished(tutorial.isPublished());
//            return tutorialRepository.save(_tutorial);
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND); }
//    }

}