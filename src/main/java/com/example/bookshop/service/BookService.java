package com.example.bookshop.service;

import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.User;
import com.example.bookshop.enums.Errors;
import com.example.bookshop.models.BookForBy;
import com.example.bookshop.models.ResultMessage;
import com.example.bookshop.models.UserInfo;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
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


    private List<UUID> genLicenseCodeForBook(int numOfBook){
        List<UUID> ret = new ArrayList<>();
        for (var i = 0; i < numOfBook; i++) {
            ret.add(UUID.randomUUID());
        }
        return ret;
    }

    public ResultMessage byBook(UserInfo info) {
        var resultMessage = new ResultMessage();

        Optional<User> userOpt = userRepository.findById(info.getUserId());
        if (userOpt.isEmpty()) {
            resultMessage.appendError(Errors.USER_NOT_FOUND);
            return resultMessage;
        }

        List<Long> bookIds = info.getBooks().stream().map(BookForBy::getId).collect(Collectors.toList());
        List<Book> bookOpt = (List<Book>) bookRepository.findByIdIn(bookIds);
        if (bookOpt.size() <= bookIds.size()) {
            resultMessage.appendError(Errors.BOOK_NOT_FOUND);
            bookIds.removeAll(bookOpt.stream().map(Book::getId).collect(Collectors.toList()));
            resultMessage.setMessage(bookIds);
            return resultMessage;
        }


        var user = userOpt.get();

        double allPrice = info.getBooks().stream()
                .mapToDouble(el -> {
                    var foundBook = bookOpt.stream().filter(book -> book.getId().equals(el.getId())).findFirst();
                    return foundBook.get().getPrice() * el.getCount();
                })
                .sum();

        if (user.getMoney() < allPrice) {
            resultMessage.appendError(Errors.NOT_ENOUGH_MONEY);
            return resultMessage;
        }

        user.setMoney(user.getMoney() - allPrice);
        userRepository.save(user);
        List<UUID> licenseCodes = genLicenseCodeForBook(bookIds.size());
        resultMessage.setMessage(licenseCodes);

        return resultMessage;
    }
}
