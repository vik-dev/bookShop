package com.example.bookshop.enums;

import lombok.Getter;

public enum Errors {
    BOOK_NOT_FOUND(1),
    UNEXPECTED_EXCEPTION(2),
    BOOK_ALREADY_EXISTS(3),
    NOT_ENOUGH_MONEY(4),
    USER_ALREADY_EXISTS(5),
    USER_NOT_FOUND(6);

    Errors(int code) {
        this.code = code;
    }

    @Getter
    private int code;
}
