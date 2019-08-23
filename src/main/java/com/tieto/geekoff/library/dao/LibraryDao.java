package com.tieto.geekoff.library.dao;


import com.tieto.geekoff.library.frontend.models.Book;

import java.util.List;

public interface LibraryDao {
    List<Book> getBooks();
    void bookIsNotAvailable(int id);
    void bookIsAvailable(int id);
    List<Book> getAllLendedBooks();

}
