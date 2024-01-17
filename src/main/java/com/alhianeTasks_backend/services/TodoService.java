package com.alhianeTasks_backend.services;

import com.alhianeTasks_backend.components.CursorUtil;

import com.alhianeTasks_backend.exceptions.ResourcesNotFoundException;

import com.alhianeTasks_backend.mapper.TodoMapper;

import com.alhianeTasks_backend.model.dtos.TodoDto;

import com.alhianeTasks_backend.model.entities.Todo;

import com.alhianeTasks_backend.model.inputs.TodoInput;

import com.alhianeTasks_backend.repositories.TodoRepo;

import graphql.relay.*;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo todoRepo;

    private final CursorUtil cursorUtil;

    public TodoDto findTodoById(UUID todoId) {

        Optional<Todo> findTodo = todoRepo.findById(todoId);

        if (findTodo.isPresent()) {

            return TodoMapper.mapToDto(findTodo.get());

        }

        throw new ResourcesNotFoundException(

            "You have not a todo with given id!"

        );

    }

    public List<TodoDto> findAllTodos() {

        return new ArrayList<>(todoRepo.findAll(

            Sort.by(Sort.Direction.ASC, "createdAt")

        ).stream().map(TodoMapper::mapToDto).toList());

    }

    public TodoConnection<TodoDto> findTodos(

        int first, String after, int last, String before, Integer skip

    ) {

        List<TodoDto> todoDtos = getTodos(after, before);

        List<Edge<TodoDto>> edges = new ArrayList<>(

            todoDtos.subList((skip != null) && (skip > 0) ?

                (first == 0 ? last : first) * skip : 0,

                todoDtos.size()

            ).stream()

            .map(todo -> new DefaultEdge<>(

                todo, cursorUtil.encode(todo.getId())

            ))

            .limit(first == 0 ? last : first)

            .toList()

        );

        if (before != null) Collections.reverse(edges);

        var pageInfo = new DefaultPageInfo(

            cursorUtil.getFirstCursor(edges),

            cursorUtil.getLastCursor(edges),

            after != null,

            edges.size() >= first

        );

        return new TodoConnection<>(todoRepo.findAll().size(), edges, pageInfo);

    }

    private List<TodoDto> getTodos(String after, String before) {

        if (after != null) {

            return todoRepo.getTodosAfter(cursorUtil.decode(after), findAllTodos());

        } else if (before != null) {

            return todoRepo.getTodosBefore(cursorUtil.decode(before), findAllTodos());

        } else {

            return findAllTodos();

        }

    }

    public TodoDto createTodo(TodoInput todoInput) {

        return TodoMapper.mapToDto(todoRepo.save(Todo.builder()

            .todoTitle(todoInput.getTodoTitle())

            .todoDesc(todoInput.getTodoDesc())

        .build()));

    }

    public TodoDto updateTodoById(TodoInput todoInput) {

        findTodoById(todoInput.getId());

        return TodoMapper.mapToDto(todoRepo.save(Todo.builder()

            .id(todoInput.getId())

            .todoTitle(todoInput.getTodoTitle())

            .todoDesc(todoInput.getTodoDesc())

        .build()));

    }

    public void deleteTodoById(UUID todoId) {

        findTodoById(todoId);

        todoRepo.deleteById(todoId);

    }

    public boolean confirmTodoById(UUID todoId) {

        TodoDto findTodo = findTodoById(todoId);

        findTodo.setConfirmed(!findTodo.isConfirmed());

        todoRepo.save(TodoMapper.mapToEntity(findTodo));

        return findTodo.isConfirmed();

    }

}
