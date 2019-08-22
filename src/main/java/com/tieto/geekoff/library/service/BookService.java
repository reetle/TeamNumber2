package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.frontend.models.Book;

public interface BookService {
    Book getBook(String code);

    void saveBook(Book book);
    boolean isBookAvailable(String code);
    boolean isBookInDatabase(String code);
}
