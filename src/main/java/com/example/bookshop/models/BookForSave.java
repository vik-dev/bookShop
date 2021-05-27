package com.example.bookshop.models;
import com.example.bookshop.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookForSave {
    private double price;
    private String name;
    private String img;

    public Book convertToBook() {
        var book = new Book();
        book.setPrice(price);
        book.setName(name);
        book.setImg(img);
        return book;
    }
}
