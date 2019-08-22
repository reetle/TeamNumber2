package com.tieto.geekoff.library.dao;

import com.tieto.geekoff.library.frontend.models.Book;

public interface BookDao {
    Book getBook(int id);

    void saveBook(Book book);
    boolean isBookAvailable(int code);
    boolean isBookInDatabase(int code);
}
