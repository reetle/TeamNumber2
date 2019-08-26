package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;

import java.util.List;

public interface LibraryService {

    List<Book> getBooks();
    List<Book> getAllLendedBooks();
    void bookIsNotAvailable(Person person, Book book);
    void bookIsAvailable(int id);
    List<Person> getAllPersons();
}
