package com.tieto.geekoff.library.dao;

import com.tieto.geekoff.library.dao.entityes.PersonEnt;
import com.tieto.geekoff.library.frontend.models.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.SQLException;

public interface PersonDao {
   String loadPerson(Person person) throws IOException;

   void savePerson(Person person) throws IOException, SQLException;
}
