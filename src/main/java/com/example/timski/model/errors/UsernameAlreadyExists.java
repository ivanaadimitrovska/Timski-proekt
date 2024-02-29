package com.example.timski.model.errors;

public class UsernameAlreadyExists extends RuntimeException{
    public UsernameAlreadyExists(String username) {

        super(String.format("Username %s already exist", username));
    }
}
