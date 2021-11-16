package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotSamePasswordException extends ResponseStatusException {
    public NotSamePasswordException() {
        super(HttpStatus.BAD_REQUEST, "Repeat password correctly!");
    }
}
