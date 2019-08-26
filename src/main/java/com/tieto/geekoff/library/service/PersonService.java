package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PersonService {

   boolean checkAccountAlreadyExist(String email);
   boolean createUser(Person person) throws SQLException;
   Person loadUser(String email);
   void addBookToPerson(Person person, Book book);
   List<Book> getBorrowedBooks(Person person);
   boolean checkEmail(Person person);
   void removeBookFromPerson(Person person, Book book);
   boolean isAdmin(Person person);
   List<Book> getLendingHistory(Person person);
   List<Person> getPersons();
   Person getPerson(int id);
   void updatePerson(Person person);
   void deletePerson(int id);
   Map<Person, List<Book>> getAllHistory();
}
