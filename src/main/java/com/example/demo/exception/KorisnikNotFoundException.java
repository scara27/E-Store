package com.example.demo.exception;

public class KorisnikNotFoundException extends RuntimeException {
    public KorisnikNotFoundException(String message) {
        super(message);
    }
}