package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.*;

@RestController
public class ApiController {
    private final TutorialRepository tutorialRepository;
    public ApiController(TutorialRepository tutorialRepository)
    {
        this.tutorialRepository = tutorialRepository;
    }

    @GetMapping("/tutorials")
    public List<Tutorial> getAllTutorials(@RequestParam(required =
            false) String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();
        if (title == null) {
            tutorials.addAll(tutorialRepository.findAll());
        } else {
            tutorials.addAll(tutorialRepository.findByTitleContaining(title));
        }
        return tutorials;
    }
    @GetMapping("/tutorials/{id}")
    public Tutorial getTutorialById(@PathVariable("id") long id) {
        Optional<Tutorial> tutorialData =
                tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            return tutorialData.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); }
    }
    @PostMapping("/tutorials")
    public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
        return tutorialRepository
                .save(new Tutorial(tutorial.getTitle(),
                        tutorial.getDescription(), false));
    }
    @PutMapping("/tutorials/{id}")
    public Tutorial updateTutorial(@PathVariable("id") long id,
                                   @RequestBody Tutorial tutorial) {
        Optional<Tutorial> tutorialData =
                tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return tutorialRepository.save(_tutorial);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); }
    }
    @DeleteMapping("/tutorials/{id}")
    public HttpStatus deleteTutorial(@PathVariable("id") long id) {
        tutorialRepository.deleteById(id);
        return HttpStatus.NO_CONTENT; }
    @DeleteMapping("/tutorials")
    public HttpStatus deleteAllTutorials() {
        tutorialRepository.deleteAll();
        return HttpStatus.NO_CONTENT; }
    @GetMapping("/tutorials/published")
    public List<Tutorial> findByPublished() {
        return tutorialRepository.findByPublished(true);
    } }