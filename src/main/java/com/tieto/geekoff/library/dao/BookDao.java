package com.tieto.geekoff.library.dao;

import com.tieto.geekoff.library.frontend.models.Book;

public interface BookDao {
    Book getBook(int id);

    void saveBook(Book book);

    void updateBook(Book book);
    void deleteBook(int bookid);

    boolean isBookAvailable(int code);
    boolean isBookInDatabase(int code);
}
