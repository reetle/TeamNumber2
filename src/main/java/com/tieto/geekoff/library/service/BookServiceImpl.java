package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.dao.BookDao;
import com.tieto.geekoff.library.frontend.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;


    public Book getBook(int id) {
        return bookDao.getBook(id);
    }


    public void saveBook(Book book) {
        bookDao.saveBook(book);
    }


    public boolean isBookAvailable(int code) {
        return bookDao.isBookAvailable(code);
    }

    public boolean isBookInDatabase(int code) {
        return bookDao.isBookInDatabase(code);
    }
}
