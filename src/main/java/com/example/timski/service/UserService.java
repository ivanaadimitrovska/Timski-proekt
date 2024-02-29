package com.example.timski.service;

import com.example.timski.model.User;
import com.example.timski.model.enums.Role;
import com.example.timski.model.errors.InvalidUsernameOrPassword;
import com.example.timski.model.errors.PasswordsDoNotMatch;
import com.example.timski.model.errors.UsernameAlreadyExists;
import com.example.timski.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }



    public User register(String username, String password, String repeatPassword, String name, String surname, Role userRole, String email) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPassword();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatch();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExists(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,userRole,email);
        return userRepository.save(user);
    }
}


