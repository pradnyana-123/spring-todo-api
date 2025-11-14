package com.example.spring_todo_api.controller;

import com.example.spring_todo_api.model.CreateTodoRequest;
import com.example.spring_todo_api.model.WebResponse;
import com.example.spring_todo_api.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping(path = "/api/users/{userId}/todos")
    public ResponseEntity<WebResponse<Object>> create(@RequestBody CreateTodoRequest data, @PathVariable Integer userId) {
        todoService.create(userId, data);

        return new ResponseEntity<>(WebResponse.builder().data("Todo created").status(201).build(), HttpStatus.OK);
    }

}
