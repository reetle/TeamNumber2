package com.tieto.geekoff.library.dao;

import com.tieto.geekoff.library.frontend.models.Book;

public interface BookDao {
    Book getBook(int id);
}
