package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.LibraryException;
import com.tieto.geekoff.library.dao.PersonDao;
import com.tieto.geekoff.library.dao.impl.PersonDaoImpl;
import com.tieto.geekoff.library.frontend.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {


   private PersonDao personDao;

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
}
