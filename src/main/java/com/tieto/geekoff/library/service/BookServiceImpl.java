package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.dao.BookDao;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;


    public Book getBook(String code) {
        return bookDao.getBook(code);
    }


    public Book getBook(int id) {
        return bookDao.getBook(id);
    }


    public void saveBook(Book book) {
        bookDao.saveBook(book);
    }

    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    public void deleteBook (int bookid) {
        bookDao.deleteBook(bookid);
    }


    public boolean isBookAvailable(String code) {
        return bookDao.isBookAvailable(code);
    }

    public boolean isBookInDatabase(String code) {
        return bookDao.isBookInDatabase(code);
    }

    public boolean doIHaveThisBook(int id, Person person) {
        return bookDao.doIHaveThisBook(id, person);
    }

}
