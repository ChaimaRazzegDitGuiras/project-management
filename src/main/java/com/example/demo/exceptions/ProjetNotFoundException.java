package com.example.demo.exceptions;

public class ProjetNotFoundException extends RuntimeException {

    public ProjetNotFoundException(String message) {
        super(message);
    }
}