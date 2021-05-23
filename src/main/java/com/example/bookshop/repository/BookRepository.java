package com.example.bookshop.repository;

import com.example.bookshop.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Iterable<Book> findAllByBought(boolean bought);
    Optional<Book> findByName(String name);
    Optional<Book> findById(Integer id);
}
