package com.example.timski.model.errors;

public class InvalidUser extends RuntimeException{

    public InvalidUser(){
        super("Invalid User");
    }
}
