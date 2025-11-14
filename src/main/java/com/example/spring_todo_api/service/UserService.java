package com.example.spring_todo_api.service;

import com.example.spring_todo_api.entity.User;
import com.example.spring_todo_api.model.RegisterUserRequest;
import com.example.spring_todo_api.repository.UserRepository;
import com.example.spring_todo_api.security.BCrypt;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private ValidationService validator;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void register(RegisterUserRequest data) {
       validator.validate(data);

       if(userRepository.existsByUsername(data.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already in use");
       }

       User user = new User();   

       user.setUsername(data.getUsername());
       user.setEmail(data.getEmail());
       user.setPassword(BCrypt.hashpw(data.getPassword(), BCrypt.gensalt()));

       userRepository.save(user);
    }
}
