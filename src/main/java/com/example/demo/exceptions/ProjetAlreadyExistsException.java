package com.example.demo.exceptions;

public class ProjetAlreadyExistsException extends RuntimeException {

    public ProjetAlreadyExistsException(String message) {
        super(message);
    }
}