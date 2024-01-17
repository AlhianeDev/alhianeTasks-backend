package com.alhianeTasks_backend.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.scalars.ExtendedScalars;

@Configuration
public class ScalarConfig {

    @Bean
    public RuntimeWiringConfigurer dateTime() {

        return wiringBuilder -> {

            wiringBuilder.scalar(ExtendedScalars.DateTime);

        };

    }

}
