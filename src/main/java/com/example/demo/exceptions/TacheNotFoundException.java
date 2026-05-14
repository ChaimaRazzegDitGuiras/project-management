package com.example.demo.exceptions;

public class TacheNotFoundException extends RuntimeException {
    public TacheNotFoundException(String message) {
        super(message);
    }
}