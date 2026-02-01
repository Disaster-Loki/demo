package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private String message;
    private String path;

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
        this.path = "";
    }
}
