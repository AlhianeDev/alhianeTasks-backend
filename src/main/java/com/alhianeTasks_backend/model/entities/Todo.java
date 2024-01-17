package com.alhianeTasks_backend.model.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;

import lombok.Builder;

import lombok.Data;

import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;

import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

import java.util.UUID;

@Entity
@Table(name = "todos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "todo_title")
    private String todoTitle;

    @Column(name = "todo_desc")
    private String todoDesc;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "is_confirmed")
    private boolean isConfirmed = false;

}
