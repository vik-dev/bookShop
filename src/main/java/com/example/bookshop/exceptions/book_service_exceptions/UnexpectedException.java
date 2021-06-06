package com.example.bookshop.exceptions.book_service_exceptions;

import com.example.bookshop.enums.Errors;

public class UnexpectedException extends BookServiceException{
    public UnexpectedException(String message) {
        super(message, Errors.UNEXPECTED_EXCEPTION);
    }
}
