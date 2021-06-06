package com.example.bookshop.exceptions.book_service_exceptions;

import com.example.bookshop.enums.Errors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class BookServiceException extends Exception {

    @Getter
    private Errors error;

    protected BookServiceException(String message, Errors error) {
        super(message);
        this.error = error;
    }

}
