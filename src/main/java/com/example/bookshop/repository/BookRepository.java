package com.example.bookshop.repository;

import com.example.bookshop.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {
    Iterable<Book> findAllByBought(boolean bought);
    Iterable<Book> findByName(String name);
}
