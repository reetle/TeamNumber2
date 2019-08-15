package com.tieto.geekoff.library.dao;

import com.tieto.geekoff.library.frontend.models.Book;

public interface BookDao {
    Book createBook(int id, String name, String author, String status, String review, int code);
    Book getBook(int id);
}
