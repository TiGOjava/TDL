package com.ToDoList.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationError {

    private int code;
    private String message;
    private String details;
}
