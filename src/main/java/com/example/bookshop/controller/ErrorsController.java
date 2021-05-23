package com.example.bookshop.controller;

import com.example.bookshop.enums.Errors;
import com.example.bookshop.mesasages.ErrorMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/errors")
public class ErrorsController {
    @GetMapping("/get_all")
    public List<ErrorMessage> getAll() {
        return Arrays.stream(Errors.values())
                .map(el -> new ErrorMessage(el.getCode(), el.getDescription()))
                .collect(Collectors.toList());
    }
}
