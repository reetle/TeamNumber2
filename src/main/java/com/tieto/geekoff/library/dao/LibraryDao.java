package com.tieto.geekoff.library.dao;


import com.tieto.geekoff.library.frontend.models.Book;

import java.util.List;

public interface LibraryDao {
    List<Book> getBooks();
    void updateBook(int id);

}
