package com.alhianeTasks_backend.resolvers.todo_resolvers;

import com.alhianeTasks_backend.model.dtos.TodoDto;

import com.alhianeTasks_backend.services.TodoService;

import com.alhianeTasks_backend.services.TodoConnection;

import graphql.kickstart.tools.GraphQLQueryResolver;

import jakarta.annotation.Nullable;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.List;

import java.util.UUID;

@Component
public class TodoQuery implements GraphQLQueryResolver {

    @Autowired
    private TodoService todoService;

    public TodoDto findTodoById(UUID todoId) {

        return todoService.findTodoById(todoId);

    }

    public List<TodoDto> findAllTodos() {

        return todoService.findAllTodos();

    }

    public TodoConnection<TodoDto> findTodos(

        int first, @Nullable String after,

        int last, @Nullable String before,

        Integer skip

    ) {

        return todoService.findTodos(first, after, last, before, skip);

    }

}
