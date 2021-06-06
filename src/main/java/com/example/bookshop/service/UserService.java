package com.example.bookshop.service;

import com.example.bookshop.entity.User;
import com.example.bookshop.enums.Errors;
import com.example.bookshop.exceptions.UserException;
import com.example.bookshop.exceptions.book_service_exceptions.BookServiceException;
import com.example.bookshop.exceptions.book_service_exceptions.UserAlreadyExistsException;
import com.example.bookshop.exceptions.book_service_exceptions.UserNotFoundException;
import com.example.bookshop.models.ResultMessage;
import com.example.bookshop.models.UserForSave;
import com.example.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public void addUser(User userForSave) throws UserException {
        List<BookServiceException> bookServiceExceptions = new ArrayList<>();
        Optional<User> user = userRepository.findUserByName(userForSave.getName());
        if (user.isPresent()) {
            bookServiceExceptions.add(new UserAlreadyExistsException(user.get().getName()));
            throw new UserException(bookServiceExceptions);
        }
        userRepository.save(userForSave);
    }

    public void addMoneyToUser(UserForSave userForSave) throws UserException {
        List<BookServiceException> bookServiceExceptions = new ArrayList<>();
        Optional<User> userOpt = userRepository.findById(userForSave.getId());
        if (userOpt.isEmpty()) {
            bookServiceExceptions.add(new UserNotFoundException(userForSave.getId()));
            throw new UserException(bookServiceExceptions);
        }
        var user = userOpt.get();
        user.setMoney(user.getMoney() + userForSave.getMoney());
        userRepository.save(user);
    }

    public User getUser(String name) throws UserNotFoundException {
        return userRepository.findUserByName(name).orElseThrow(() -> new UserNotFoundException(name));
    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }
}
