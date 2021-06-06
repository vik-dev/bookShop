package com.example.bookshop.exceptions.book_service_exceptions;

import com.example.bookshop.enums.Errors;


public class UserNotFoundException extends BookServiceException {
    public UserNotFoundException(Long userId) {
        super(String.format("Пользователь не найден %s", userId.toString()), Errors.USER_NOT_FOUND);
    }

    public UserNotFoundException(String userName) {
        super(String.format("Пользователь не найден %s", userName), Errors.USER_NOT_FOUND);
    }
}
