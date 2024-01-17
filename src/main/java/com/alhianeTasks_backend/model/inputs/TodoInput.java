package com.alhianeTasks_backend.model.inputs;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoInput {

    private UUID id;

    @NotBlank(message = "Todo Title Can't Be Blank!")
    private String todoTitle;

    @NotBlank(message = "Todo Description Can't Be Blank!")
    private String todoDesc;

}
