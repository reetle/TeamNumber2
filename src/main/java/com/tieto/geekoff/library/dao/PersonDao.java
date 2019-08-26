package com.tieto.geekoff.library.dao;

import com.tieto.geekoff.library.dao.entityes.PersonEnt;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PersonDao {

   boolean checkAccountAlreadyExist(String email);
   boolean createUser(Person person) throws SQLException;
   Person loadUser(String email);
   void addBookToPerson(Person person, Book book);
   List<Book> getBorrowedBooks(Person person);
   boolean checkEmail(Person person);
   void removeBookFromPerson(Person person, Book book);
   boolean isAdmin(Person person);
   List<Book> getLendingHistory(Person person);
}
