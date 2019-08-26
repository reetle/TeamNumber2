package com.tieto.geekoff.library.dao;


import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;

import java.util.List;

public interface LibraryDao {
    List<Book> getBooks();
    void bookIsNotAvailable(Person person, Book book);
    void bookIsAvailable(int id);
    List<Book> getAllLendedBooks();
    List<Person> getAllPersons();
}
