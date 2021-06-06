package com.example.bookshop.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum Errors {
    BOOK_NOT_FOUND(1, "Книга не найдена"),
    UNEXPECTED_EXCEPTION(2, "Неизвестная ошибка"),
    BOOK_ALREADY_EXISTS(3, "Книга уже существует"),
    NOT_ENOUGH_MONEY(4, "Не достаточно денег"),
    USER_ALREADY_EXISTS(5, "Пользователь уже существует"),
    USER_NOT_FOUND(6, "Пользователь не найден");

    Errors(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private final int code;

    @Getter
    private final String description;

    @JsonValue
    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.format("error code - %d, description - %s", this.code, this.description);
    }
}
