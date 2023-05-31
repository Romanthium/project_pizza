package com.example.projectpizza.controller.handler;

import com.example.projectpizza.handler.IdNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(IdNotFoundException.class)
    public String idNotFoundException(IdNotFoundException ex) {
        return "error";
    }
}
