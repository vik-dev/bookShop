package com.example.bookshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookForBy {
    /**
     * иеднтификатор книги
     */
    private Long id;
    /**
     * количество книг
     */
    private Integer count;
}
