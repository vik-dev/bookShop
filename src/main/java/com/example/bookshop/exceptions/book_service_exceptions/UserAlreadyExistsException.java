package com.example.bookshop.exceptions.book_service_exceptions;

import com.example.bookshop.enums.Errors;

public class UserAlreadyExistsException extends BookServiceException{
    public UserAlreadyExistsException(String message) {
        super(String.format("User already exists %s", message), Errors.USER_ALREADY_EXISTS);
    }
}
