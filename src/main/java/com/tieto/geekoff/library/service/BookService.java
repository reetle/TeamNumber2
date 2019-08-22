package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;

public interface BookService {
    Book getBook(String code);
    Book getBook(int id);

    void saveBook(Book book);

    boolean isBookAvailable(String code);
    boolean isBookInDatabase(String code);
    boolean doIHaveThisBook(int id, Person person);


    void updateBook(Book book);

    void deleteBook(int bookid);

   

}
