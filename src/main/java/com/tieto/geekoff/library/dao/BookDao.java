package com.tieto.geekoff.library.dao;

import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;

import java.util.List;

public interface BookDao {
    Book getBook(String code);
    Book getBook(int id);
    void saveBook(Book book);
    boolean isBookAvailable(String code);
    boolean isBookInDatabase(String code);
    boolean doIHaveThisBook(String code, Person person);
    void updateBook(Book book);
    void deleteBook(int bookid);

   
}
