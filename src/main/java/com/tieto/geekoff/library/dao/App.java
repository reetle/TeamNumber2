package com.tieto.geekoff.library.dao;

import com.tieto.geekoff.library.frontend.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 *
 * @author postgresqltutorial.com
 */

public class App{
 
    private final String url = "jdbc:postgresql://192.168.1.202:5432/postgres";
    private final String user = "postgres";
    private final String password = "postgres";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


    public void createUser(Person user) throws SQLException {

        String sql = "INSERT INTO persondata ("
                + " firstname,"
                + " lastname,"
                + " email) VALUES ("
                + "?, ?, ?)";
        try (
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS)
        ) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());


            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }


 
    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) throws SQLException {

        App app = new App();
        Person person = new Person();
        person.setFirstName("Mati");
        person.setSurname("Kati");
        person.setEmail("mati@kati.ee");

        app.createUser(person);
    }

     */
}