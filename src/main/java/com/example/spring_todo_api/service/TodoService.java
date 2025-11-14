package com.example.spring_todo_api.service;

import com.example.spring_todo_api.entity.Todo;
import com.example.spring_todo_api.entity.User;
import com.example.spring_todo_api.model.CreateTodoRequest;
import com.example.spring_todo_api.repository.TodoRepository;
import com.example.spring_todo_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ValidationService validator;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void create(Integer userId, CreateTodoRequest data) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        boolean existingTodo = todoRepository.existsByTitle(data.getTitle());

        if(existingTodo) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo already exists");
        }

        Todo todo = new Todo();

        todo.setUser(user);
        todo.setTitle(data.getTitle());
        todo.setDescription(data.getDescription());

        todoRepository.save(todo);
    }

}
