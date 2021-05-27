package com.example.bookshop.repository;

import com.example.bookshop.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByName(String name);
    Optional<Book> findById(Long id);
    Iterable<Book> findByIdIn(List<Long> ids);
}
