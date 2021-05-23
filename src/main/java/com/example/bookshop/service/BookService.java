package com.example.bookshop.service;

import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.User;
import com.example.bookshop.enums.Errors;
import com.example.bookshop.mesasages.ResultMessage;
import com.example.bookshop.mesasages.UserInfo;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAllByBought(false);
    }

    public ResultMessage saveBook(@Validated Book book) {
        var resultMessage = new ResultMessage();
        Optional<Book> byId = bookRepository.findByName(book.getName());
        if (byId.isPresent()) {
            resultMessage.appendError(Errors.BOOK_ALREADY_EXISTS);
        } else {
            bookRepository.save(book);
        }
        return resultMessage;
    }

    @Transactional
    public void deleteBook(String name){
        Optional<Book> byName = bookRepository.findByName(name);
        byName.ifPresent(book -> bookRepository.delete(book));
    }

    public ResultMessage byBook(UserInfo info) {
        var resultMessage = new ResultMessage();
        Optional<Book> bookOpt = bookRepository.findById(info.getBookId());
        if (bookOpt.isEmpty()) {
            resultMessage.appendError(Errors.BOOK_NOT_FOUND);
            return resultMessage;
        }

        Optional<User> userOpt = userRepository.findUserByName(info.getUserName());
        if (userOpt.isEmpty()) {
            resultMessage.appendError(Errors.USER_NOT_FOUND);
            return resultMessage;
        }

        var user = userOpt.get();
        var book = bookOpt.get();

        if (user.getMoney() < book.getPrice()) {
            resultMessage.appendError(Errors.NOT_ENOUGH_MONEY);
            return resultMessage;
        }

        book.setBought(true);
        bookRepository.save(book);
        user.setMoney(user.getMoney() - book.getPrice());
        userRepository.save(user);
        return resultMessage;
    }
}
