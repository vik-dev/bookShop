package com.example.bookshop.exceptions.book_service_exceptions;

import com.example.bookshop.enums.Errors;

import java.util.List;
import java.util.stream.Collectors;

public class BookNotFoundException extends BookServiceException {
    public BookNotFoundException(List<Long> bookIds) {
        super(String.format("Books not found - %s", bookIds.stream().map(el -> el.toString() + ", ").collect(Collectors.joining())), Errors.BOOK_NOT_FOUND);
    }
}
