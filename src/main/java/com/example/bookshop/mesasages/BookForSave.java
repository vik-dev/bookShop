package com.example.bookshop.mesasages;
import com.example.bookshop.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookForSave {
    private String price;
    private String name;

    public Book convertToBook() {
        var book = new Book();
        book.setPrice(Double.parseDouble(price));
        book.setName(name);
        return book;
    }
}
