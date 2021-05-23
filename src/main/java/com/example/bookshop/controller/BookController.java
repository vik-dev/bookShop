package com.example.bookshop.controller;

import com.example.bookshop.entity.Book;
import com.example.bookshop.mesasages.BookForSave;
import com.example.bookshop.mesasages.ResultMessage;
import com.example.bookshop.mesasages.UserInfo;
import com.example.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/by")
    public ResponseEntity<ResultMessage> byBook(@RequestBody UserInfo info) {
        var resultMessage = bookService.byBook(info);
        if (resultMessage.isSuccess()) {
            return ResponseEntity.ok(resultMessage);
        } else {
            return ResponseEntity.badRequest().body(resultMessage);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ResultMessage> saveBook(@RequestBody BookForSave book) {
        var resultMessage = bookService.saveBook(book.convertToBook());
        if (resultMessage.isSuccess()) {
            return ResponseEntity.ok(resultMessage);
        } else {
            return ResponseEntity.badRequest().body(resultMessage);
        }
    }

    @GetMapping("/getAll")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @DeleteMapping("/delete/{name}")
    public void deleteBook(@PathVariable String name){
        bookService.deleteBook(name);
    }
}
