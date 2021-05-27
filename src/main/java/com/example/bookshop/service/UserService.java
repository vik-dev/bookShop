package com.example.bookshop.service;

import com.example.bookshop.entity.User;
import com.example.bookshop.enums.Errors;
import com.example.bookshop.models.ResultMessage;
import com.example.bookshop.models.UserForSave;
import com.example.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public ResultMessage addUser(User userForSave) {
        var resultMessage = new ResultMessage();
        Optional<User> user = userRepository.findUserByName(userForSave.getName());
        if (user.isPresent()) {
            resultMessage.appendError(Errors.USER_ALREADY_EXISTS);
            return resultMessage;
        }
        userRepository.save(userForSave);
        return resultMessage;
    }

    public ResultMessage addMoneyToUser(UserForSave userForSave) {
        var resultMessage = new ResultMessage();
        Optional<User> userOpt = userRepository.findUserByName(userForSave.getName());
        if (userOpt.isEmpty()) {
            resultMessage.appendError(Errors.USER_NOT_FOUND);
            return resultMessage;
        }
        User user = userOpt.get();
        user.setMoney(user.getMoney() + userForSave.getMoney());
        userRepository.save(user);
        return resultMessage;
    }

    public User getUser(String name) {
        return userRepository.findUserByName(name).orElse(new User());
    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }
}
