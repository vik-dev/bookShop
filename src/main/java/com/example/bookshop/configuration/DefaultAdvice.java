package com.example.bookshop.configuration;

import com.example.bookshop.entity.User;
import com.example.bookshop.exceptions.UserException;
import com.example.bookshop.exceptions.book_service_exceptions.BookServiceException;
import com.example.bookshop.exceptions.book_service_exceptions.UserNotFoundException;
import com.example.bookshop.models.ResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = CustomExceptionHandler.class)
public class DefaultAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAdvice.class);

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResultMessage> handleException(UserException e) {
        var resultMessage = new ResultMessage();
        LOGGER.error(e.getMessage(), e);
        for (BookServiceException exception : e.getExceptions()) {
            resultMessage.appendError(exception.getError());
        }
        return ResponseEntity.badRequest().body(resultMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<User> handleException(UserNotFoundException e) {
        LOGGER.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(new User());
    }
}
