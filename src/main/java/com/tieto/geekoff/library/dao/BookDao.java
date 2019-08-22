package com.tieto.geekoff.library.dao;

import com.tieto.geekoff.library.frontend.models.Book;

public interface BookDao {
    Book getBook(String code);

    void saveBook(Book book);
    boolean isBookAvailable(String code);
    boolean isBookInDatabase(String code);
}
