package com.example.timski.service;

import com.example.timski.model.errors.InvalidUser;
import com.example.timski.model.User;
import com.example.timski.model.errors.InvalidArgumentsException;
import com.example.timski.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class Authentication {

    private final UserRepository userRepository;

    public Authentication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUser::new);
    }

}
