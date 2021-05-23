package com.example.bookshop.controller;

import com.example.bookshop.entity.User;
import com.example.bookshop.mesasages.ResultMessage;
import com.example.bookshop.mesasages.UserForSave;
import com.example.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ResultMessage> createUser(@RequestBody UserForSave userForSave){
        var resultMessage = userService.addUser(userForSave.convertToUser());
        if (resultMessage.isSuccess()){
            return ResponseEntity.ok(resultMessage);
        } else {
            return ResponseEntity.badRequest().body(resultMessage);
        }
    }

    @PostMapping("/add_money")
    public ResponseEntity<ResultMessage> addMoney(@RequestBody UserForSave userForSave){
        var resultMessage = userService.addMoneyToUser(userForSave);
        if (resultMessage.isSuccess()){
            return ResponseEntity.ok(resultMessage);
        } else {
            return ResponseEntity.badRequest().body(resultMessage);
        }
    }

    @GetMapping("/get/{name}")
    public User getUser(@PathVariable String name){
        return userService.getUser(name);
    }

}
