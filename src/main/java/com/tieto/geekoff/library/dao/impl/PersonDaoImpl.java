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

    public boolean checkEmail(Person person) {
        String email = person.getEmail().substring(person.getEmail().indexOf("@") + 1);
        return email.equals(tietoDomain);
    }


    public boolean checkAccountAlreadyExist(String email) {
        String sql = "SELECT email FROM persondata WHERE email = ?";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Konto juba olemas");
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }


    public boolean createUser(Person person) throws SQLException {
        String sql = "INSERT INTO persondata ("
                + " firstname,"
                + " lastname,"
                + " email) VALUES ("
                + "?, ?, ?)";

        String email = person.getEmail().toLowerCase();
        String firstName = email.substring(0, email.indexOf("."));
        String surname = email.substring(email.indexOf(".") + 1, email.indexOf("@"));

        if (checkEmail(person) && !checkAccountAlreadyExist(person.getEmail())) {
            try (
                    Connection connection = app.connect();
                    PreparedStatement statement = connection.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS)
            ) {

                statement.setString(1, firstName.substring(0, 1).toUpperCase() + firstName.substring(1));
                statement.setString(2, surname.substring(0, 1).toUpperCase() + surname.substring(1));
                statement.setString(3, person.getEmail());


                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        person.setId(generatedKeys.getInt(1));
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
        String sql = "SELECT personid, firstname, lastname, email, role FROM persondata WHERE email = ?";
        Person person = new Person();

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                person.setId(rs.getInt("personid"));
                person.setFirstName(rs.getString("firstname"));
                person.setSurname(rs.getString("lastname"));
                person.setEmail(rs.getString("email"));
                person.setRole(rs.getString("role"));
                person.setBorrowedBooks(getBorrowedBooks(person));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return person;
    }

    public boolean isAdmin(Person person) {
        return person.getRole().equals("admin");
    }


    public List<Book> getLendingHistory(Person person) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT bookid FROM orderedbookshistory WHERE personid = ?";
        String sql2 = "SELECT bookid, bookname, bookautor, status, review, code FROM bookdata WHERE bookid = ?";
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
                    book.setBookid(resultSet.getInt("bookid"));
                    book.setName(resultSet.getString("bookname"));
                    book.setAuthor(resultSet.getString("bookautor"));
                    book.setStatus(resultSet.getString("status"));
                    book.setReview(resultSet.getString("review"));
                    book.setCode(resultSet.getString("code"));
                    books.add(book);
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return books;
    }


    public void addBookToPerson(Person person, Book book) {
        String sql = "INSERT INTO orderedbooks (personid, bookid) VALUES (?,?)";
        String sqlHistory = "INSERT INTO orderedbookshistory (personid, bookid) VALUES (?,?)";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            PreparedStatement statement = conn.prepareStatement(sqlHistory);
            statement.setInt(1, person.getId());
            statement.setInt(2, book.getBookid());
            statement.executeUpdate();

            pstmt.setInt(1, person.getId());
            pstmt.setInt(2, book.getBookid());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void removeBookFromPerson(Person person, Book book) {
        String sql = "DELETE FROM orderedbooks WHERE bookid = ? AND personid = ?";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, book.getBookid());
            pstmt.setInt(2, person.getId());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public List<Book> getBorrowedBooks(Person person) {
        List<Book> books = new ArrayList<>();

        String sql = "SELECT bookid FROM orderedbooks WHERE personid = ?";
        String sql2 = "SELECT bookid, bookname, bookautor, status, review, code FROM bookdata WHERE bookid = ?";

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
                    book.setBookid(resultSet.getInt("bookid"));
                    book.setName(resultSet.getString("bookname"));
                    book.setAuthor(resultSet.getString("bookautor"));
                    book.setStatus(resultSet.getString("status"));
                    book.setReview(resultSet.getString("review"));
                    book.setCode(resultSet.getString("code"));
                    books.add(book);
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return books;
    }



}
