package com.example.bookshop.exceptions.book_service_exceptions;

import com.example.bookshop.enums.Errors;

public class NotEnoughMoneyException extends BookServiceException{
    public NotEnoughMoneyException(Double userMoney, Double allMoney) {
        super(String.format("userMoney - %f, price - %f", userMoney, allMoney), Errors.NOT_ENOUGH_MONEY);
    }
}
