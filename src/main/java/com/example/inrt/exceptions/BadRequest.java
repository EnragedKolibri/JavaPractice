package com.example.inrt.exceptions;

public class BadRequest extends RuntimeException {
    public BadRequest(String message) {
        super(message);
    }
}
