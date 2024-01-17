package com.alhianeTasks_backend.model.dtos;

import lombok.AllArgsConstructor;

import lombok.Builder;

import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TodoDto {

    private UUID id;

    private String todoTitle;

    private String todoDesc;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private boolean isConfirmed = false;

}
