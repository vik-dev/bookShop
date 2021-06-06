package com.example.bookshop.controller;

import com.example.bookshop.configuration.CustomExceptionHandler;
import com.example.bookshop.entity.Book;
import com.example.bookshop.exceptions.UserException;
import com.example.bookshop.models.BookForSave;
import com.example.bookshop.models.ResultMessage;
import com.example.bookshop.models.UserInfo;
import com.example.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CustomExceptionHandler
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/by")
    public ResponseEntity<ResultMessage> byBook(@RequestBody UserInfo info) throws UserException {
        var resultMessage = new ResultMessage();
        List<UUID> uuids = bookService.byBook(info);
        resultMessage.setMessage(uuids);
        return ResponseEntity.ok(resultMessage);
    }

    @PostMapping("/save")
    public ResponseEntity<ResultMessage> saveBook(@RequestBody BookForSave book) throws UserException{
        bookService.saveBook(book.convertToBook());
        return ResponseEntity.ok(new ResultMessage());
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
