package com.example.demo.exception;

public class ProizvodNotFoundException extends RuntimeException{
    public ProizvodNotFoundException(String message) {
        super(message);
    }
}
