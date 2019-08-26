package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.dao.LibraryDao;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryDao libraryDao;


    public List<Book> getBooks() {
        return libraryDao.getBooks();
    }


    public List<Book> getAllLendedBooks() {
        return libraryDao.getAllLendedBooks();
    }

    public void bookIsNotAvailable(Person person, Book book) {
        libraryDao.bookIsNotAvailable(person, book);
    }


    public void bookIsAvailable(int id) {
        libraryDao.bookIsAvailable(id);
    }

    public List<Person> getAllPersons() {
        return libraryDao.getAllPersons();
    }


}
