package com.example.timski.model.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class CategoryNotFound extends RuntimeException{
    public CategoryNotFound(Long id) {
        super(String.format("Category %d was not found", id));
    }
}
