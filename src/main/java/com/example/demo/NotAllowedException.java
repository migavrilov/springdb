package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotAllowedException extends ResponseStatusException {
    public NotAllowedException() {
        super(HttpStatus.FORBIDDEN, "You are not allowed to do such action");
    }
}
