package com.alhianeTasks_backend.repositories;

import com.alhianeTasks_backend.mapper.TodoMapper;
import com.alhianeTasks_backend.model.dtos.TodoDto;
import com.alhianeTasks_backend.model.entities.Todo;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TodoRepo extends JpaRepository<Todo, UUID> {

    public default List<TodoDto> getTodosAfter(UUID id, List<TodoDto> todoDtos) {

        List<TodoDto> todos = todoDtos.stream()

            .dropWhile(todo -> todo.getId().compareTo(id) != 0)

            .toList();

        return todos.subList(1, todos.size());

    }

    public default List<TodoDto> getTodosBefore(UUID id, List<TodoDto> todoDtos) {

        Collections.reverse(todoDtos);

        List<TodoDto> reversedTodos = new ArrayList<>(todoDtos.stream()

            .dropWhile(todo -> todo.getId().compareTo(id) != 0)

            .toList());

        return reversedTodos.subList(1, reversedTodos.size());

    }

}
