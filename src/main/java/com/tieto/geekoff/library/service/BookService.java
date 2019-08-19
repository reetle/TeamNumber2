package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.frontend.models.Book;

public interface BookService {
    Book getBook(int id);

    void saveBook(Book book);
}
