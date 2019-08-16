package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonService {

   boolean checkAccountAlreadyExist(Person person);
   boolean createUser(Person person) throws SQLException;
   Person loadUser(String email);
   void addBookToPerson(Person person, Book book);
   List<Book> getBorrowedBooks(Person person);
   boolean checkEmail(Person person);
   void removeBookFromPerson(Person person, Book book);

}
