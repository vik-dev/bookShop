package com.example.bookshop.controller;

import com.example.bookshop.entity.User;
import com.example.bookshop.mesasages.ResultMessage;
import com.example.bookshop.mesasages.UserForSave;
import com.example.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<User> getUser(@PathVariable String name){
        var user = userService.getUser(name);
        if (user.getName() == null) {
            return ResponseEntity.badRequest().body(user);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get_all")
    public List<User> getAll(){
        return userService.getAll();
    }

}
