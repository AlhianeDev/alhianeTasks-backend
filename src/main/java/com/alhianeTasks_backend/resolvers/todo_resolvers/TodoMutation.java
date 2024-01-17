package com.alhianeTasks_backend.resolvers.todo_resolvers;

import com.alhianeTasks_backend.model.dtos.TodoDto;

import com.alhianeTasks_backend.model.inputs.TodoInput;

import com.alhianeTasks_backend.services.TodoService;

import graphql.kickstart.tools.GraphQLMutationResolver;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Component
@Validated
public class TodoMutation implements GraphQLMutationResolver {

    @Autowired
    private TodoService todoService;

    public TodoDto createTodo(@Valid TodoInput todoInput) {

        return todoService.createTodo(todoInput);

    }

    public TodoDto updateTodoById(@Valid TodoInput todoInput) {

        return todoService.updateTodoById(todoInput);

    }

    public String deleteTodoById(UUID todoId) {

        todoService.deleteTodoById(todoId);

        return "Todo with given id has been deleted successfully.";

    }

    public boolean confirmTodoById(UUID todoId) {

        return todoService.confirmTodoById(todoId);

    }

}
