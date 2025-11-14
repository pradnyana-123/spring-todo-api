package com.example.spring_todo_api.repository;

import com.example.spring_todo_api.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    public boolean existsByTitle(String title);
    public List<Todo> findTodosByUserId(Integer userId);
}
