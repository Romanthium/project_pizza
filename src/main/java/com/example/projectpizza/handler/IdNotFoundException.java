package com.example.projectpizza.handler;

public class IdNotFoundException extends IllegalArgumentException {

    public IdNotFoundException(Integer id, String entityName) {
        super(String.format("%s with id %d not found", entityName, id));
    }
}
