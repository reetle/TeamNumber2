package com.tieto.geekoff.library.dao.impl;

import com.tieto.geekoff.library.dao.App;
import com.tieto.geekoff.library.dao.PersonDao;
import com.tieto.geekoff.library.frontend.models.Person;

import java.io.IOException;
import java.sql.SQLException;

public class PersonDaoImpl implements PersonDao {
    @Override
    public String loadPerson(Person person) throws IOException {
        return null;
    }

    @Override
    public void savePerson(Person person) throws IOException, SQLException {
        App app = new App();
        app.createUser(person);

    }
}
