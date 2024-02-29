package com.example.timski.model.errors;

public class PasswordsDoNotMatch extends RuntimeException{

    public PasswordsDoNotMatch(){
        super("PasswordDoNotMatch");
    }
}
