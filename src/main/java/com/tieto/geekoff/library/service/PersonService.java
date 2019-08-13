package com.tieto.geekoff.library.service;

import com.tieto.geekoff.library.frontend.models.Person;

public interface PersonService {

   String loadPerson(Person person);

   void savePerson(Person person);

}
