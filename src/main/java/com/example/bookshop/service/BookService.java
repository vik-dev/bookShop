package com.example.bookshop.service;

import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.User;
import com.example.bookshop.enums.Errors;
import com.example.bookshop.exceptions.UserException;
import com.example.bookshop.exceptions.book_service_exceptions.*;
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

    public void saveBook(@Validated Book book) throws UserException {
        List<BookServiceException> bookServiceExceptions = new ArrayList<>();

        Optional<Book> byId = bookRepository.findByName(book.getName());
        if (byId.isPresent()) {
            bookServiceExceptions.add(new BookAlreadyExistsException(byId.get().getName()));
            throw new UserException(bookServiceExceptions);
        } else {
            bookRepository.save(book);
        }
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

    public List<UUID> byBook(UserInfo info) throws UserException {
        List<BookServiceException> bookServiceExceptions = new ArrayList<>();
        Optional<User> userOpt = userRepository.findById(info.getUserId());
        if (userOpt.isEmpty()) {
            bookServiceExceptions.add(new UserNotFoundException(info.getUserId()));
            throw new UserException(bookServiceExceptions);
        }

        List<Long> bookIds = info.getBooks().stream().map(BookForBy::getId).collect(Collectors.toList());
        List<Book> bookOpt = (List<Book>) bookRepository.findByIdIn(bookIds);
        if (bookOpt.size() < bookIds.size()) {
            bookIds.removeAll(bookOpt.stream().map(Book::getId).collect(Collectors.toList()));
            bookServiceExceptions.add(new BookNotFoundException(bookIds));
            throw new UserException(bookServiceExceptions);
        }


        var user = userOpt.get();

        double allPrice = info.getBooks().stream()
                .mapToDouble(el -> {
                    var foundBook = bookOpt.stream().filter(book -> book.getId().equals(el.getId())).findFirst();
                    return foundBook.get().getPrice() * el.getCount();
                })
                .sum();

        if (user.getMoney() < allPrice) {
            bookServiceExceptions.add(new NotEnoughMoneyException(user.getMoney(), allPrice));
            throw new UserException(bookServiceExceptions);
        }

        user.setMoney(user.getMoney() - allPrice);
        userRepository.save(user);
        return genLicenseCodeForBook(bookIds.size());
    }
}
