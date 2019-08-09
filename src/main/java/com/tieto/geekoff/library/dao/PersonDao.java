package com.tieto.geekoff.library.dao;

import com.tieto.geekoff.library.dao.entityes.PersonEnt;
import java.io.IOException;

public interface PersonDao {
   String loadPerson() throws IOException;

   void savePerson(String person, boolean append) throws IOException;
}
