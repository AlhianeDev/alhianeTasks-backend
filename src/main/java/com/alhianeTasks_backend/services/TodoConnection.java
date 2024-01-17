package com.alhianeTasks_backend.services;

import graphql.relay.DefaultConnection;

import graphql.relay.DefaultPageInfo;

import graphql.relay.Edge;

import java.util.List;

public class TodoConnection <T> extends DefaultConnection<T> {

    public Integer totalTodos;

    public TodoConnection(

        Integer totalTodos, List<Edge<T>> edges, DefaultPageInfo pageInfo

    ) {

        super(edges, pageInfo);

        this.totalTodos = totalTodos;

    }

}
