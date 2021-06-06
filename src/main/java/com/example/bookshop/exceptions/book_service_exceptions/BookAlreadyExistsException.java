package com.example.bookshop.exceptions.book_service_exceptions;

import com.example.bookshop.enums.Errors;

public class BookAlreadyExistsException extends BookServiceException{

    public BookAlreadyExistsException(String message) {
        super(String.format("Book already exists %s", message), Errors.BOOK_ALREADY_EXISTS);
    }
}
