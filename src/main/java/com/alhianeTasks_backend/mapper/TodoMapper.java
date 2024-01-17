package com.alhianeTasks_backend.mapper;

import com.alhianeTasks_backend.model.dtos.TodoDto;

import com.alhianeTasks_backend.model.entities.Todo;

import lombok.Data;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TodoMapper {

    public static Todo mapToEntity(TodoDto todoDto) {

        return Todo.builder()

            .id(todoDto.getId())

            .todoTitle(todoDto.getTodoTitle())

            .todoDesc(todoDto.getTodoDesc())

            .createdAt(todoDto.getCreatedAt())

            .updatedAt(todoDto.getUpdatedAt())

            .isConfirmed(todoDto.isConfirmed())

        .build();

    }

    public static TodoDto mapToDto(Todo todo) {

        return TodoDto.builder()

            .id(todo.getId())

            .todoTitle(todo.getTodoTitle())

            .todoDesc(todo.getTodoDesc())

            .createdAt(todo.getCreatedAt())

            .updatedAt(todo.getUpdatedAt())

            .isConfirmed(todo.isConfirmed())

        .build();

    }

}
