package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.LibraryException;
import com.tieto.geekoff.library.dao.PersonDao;
import com.tieto.geekoff.library.dao.impl.PersonDaoImpl;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {


   @Autowired
   private PersonDao personDao;

   public boolean checkAccountAlreadyExist(String email) {
      return personDao.checkAccountAlreadyExist(email);
   }

   public boolean createUser(Person person) throws SQLException {
      return personDao.createUser(person);
   }

   public Person loadUser(String email) {
      return personDao.loadUser(email);
   }

   public void addBookToPerson(Person person, Book book) {
      personDao.addBookToPerson(person, book);
   }

   public List<Book> getBorrowedBooks(Person person) {
      return personDao.getBorrowedBooks(person);
   }

   public boolean checkEmail(Person person) {
      return personDao.checkEmail(person);
   }

   public void removeBookFromPerson(Person person, Book book) {
      personDao.removeBookFromPerson(person, book);
   }

   public boolean isAdmin(Person person) {
      return personDao.isAdmin(person);
   }

   public List<Book> getLendingHistory(Person person) {
      return personDao.getLendingHistory(person);
   }


   public List<Person> getPersons() { return personDao.getPersons(); }

   public void updatePerson(Person person) { personDao.updatePerson(person); }

   public Person getPerson(int id) { return personDao.getPerson(id); };

   public void deletePerson(int id) { personDao.deletePerson(id); }

   public Map<Person, List<Book>> getAllHistory() {
      return personDao.getAllHistory();
   }

   public String getPersonImageString(String email) {
      return personDao.getPersonImageString(email);
   }



   /*
   public String loadPerson(Person person) {
      String ret;
      try {
         ret = personDao.loadPerson(person);
      } catch (Exception e) {
         throw new LibraryException(e.getMessage(), e);
      }
      return ret;
   }

   public void savePerson(Person person) {
      try {
         personDao = new PersonDaoImpl();
         personDao.savePerson(person);

      } catch (Exception e) {
         throw new LibraryException(e.getMessage(), e);

      }
   }

    */
}
