package com.tieto.geekoff.library.dao.impl;

import com.tieto.geekoff.library.dao.App;
import com.tieto.geekoff.library.dao.PersonDao;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {

    private final String tietoDomain = "tieto.com";

    App app = new App();


    public boolean checkAccountAlreadyExist(Person person) {
        String sql = "SELECT email FROM persondata WHERE email = ?";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, person.getEmail());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Konto juba olemas");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return true;
    }


    public boolean createUser(Person person) throws SQLException {
        String sql = "INSERT INTO persondata ("
                + " firstname,"
                + " lastname,"
                + " email) VALUES ("
                + "?, ?, ?)";

        if (checkEmail(person) && checkAccountAlreadyExist(person)) {
            try (
                    Connection connection = app.connect();
                    PreparedStatement statement = connection.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS)
            ) {

                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getSurname());
                statement.setString(3, person.getEmail());


                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        person.setId(generatedKeys.getLong(1));
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
            return true;
        } else {
            System.out.println("Invalid email address!");
            return false;
        }
    }


    public Person loadUser(String email) {
        String sql = "SELECT firstname, lastname, email, personid FROM persondata WHERE email = ?";
        Person person;

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                person = new Person();
                person.setFirstName(rs.getString("firstname"));
                person.setSurname(rs.getString("lastname"));
                person.setEmail(rs.getString("email"));
                person.setId(rs.getLong("personid"));
                return person;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Person();
    }


    public void addBookToPerson(Person person, Book book) {
        String sql = "INSERT INTO orderedbooks (personid, bookid) VALUES (?,?)";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, person.getId());
            pstmt.setInt(2, book.getBookid());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public List<Book> getBorrowedBooks(Person person) {
        List<Book> books = new ArrayList<>();

        String sql = "SELECT bookid FROM orderedbooks WHERE personid = ?";
        String sql2 = "SELECT bookname, bookautor FROM bookdata WHERE bookid = ?";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, person.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PreparedStatement statement = conn.prepareStatement(sql2);
                statement.setInt(1, rs.getInt("bookid"));
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Book book = new Book();
                    book.setName(resultSet.getString("bookname"));
                    book.setAuthor(resultSet.getString("bookautor"));
                    books.add(book);
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return books;
    }


    public boolean checkEmail(Person person) {
        String email = person.getEmail().substring(person.getEmail().indexOf("@") + 1);
        return email.equals(tietoDomain);
    }
}
