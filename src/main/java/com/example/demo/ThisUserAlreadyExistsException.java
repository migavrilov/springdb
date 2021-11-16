package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ThisUserAlreadyExistsException extends ResponseStatusException {
    public ThisUserAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "This user already exists");
    }
}
