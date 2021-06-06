package com.example.bookshop.exceptions;

import com.example.bookshop.exceptions.book_service_exceptions.BookServiceException;
import lombok.Getter;

import java.util.List;

public class UserException extends Exception{

    @Getter
    private List<BookServiceException> exceptions;

    private String description;

    @Override
    public String getMessage() {
        return this.description;
    }

    public UserException(List<BookServiceException> exceptions) {
        this.exceptions = exceptions;
        var message = new StringBuilder();
        for (BookServiceException exception : exceptions) {
            message.append(exception.getError().toString()).append(". ").append(exception.getMessage()).append(";\n");
        }
        this.description = message.toString();
    }
}
