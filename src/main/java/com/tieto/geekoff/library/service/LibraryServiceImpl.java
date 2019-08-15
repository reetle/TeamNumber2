package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.dao.LibraryDao;
import com.tieto.geekoff.library.frontend.models.Book;
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

    public void updateBook(int id) {
        libraryDao.updateBook(id);
    }
}
