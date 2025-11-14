package com.example.spring_todo_api.controller;

import com.example.spring_todo_api.model.RegisterUserRequest;
import com.example.spring_todo_api.model.WebResponse;
import com.example.spring_todo_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<Object> register(@RequestBody RegisterUserRequest data) {
       userService.register(data);

       return WebResponse.builder().status(201).message("User created").build();
    }
}
