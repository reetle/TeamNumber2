package com.tieto.geekoff.library.dao;

import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author postgresqltutorial.com
 */

public class App{
 
    private final String url = "jdbc:postgresql://192.168.1.202:5432/postgres";
    private final String user = "postgres";
    private final String password = "postgres";
    private final String tietoDomain = "tieto.com";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private boolean checkEmail(Person person) {
        String email = person.getEmail().substring(person.getEmail().indexOf("@") + 1);
        System.out.println(email);
        return email.equals(tietoDomain);
    }

    private boolean checkAccountAlreadyExist(Person person) {

        String sql = "SELECT email FROM persondata WHERE email = ?";

        try (Connection conn = connect();
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
                    Connection connection = connect();
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

        try (Connection conn = connect();
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

        return null;


    }

    public List<Book> getBooks(){
        String sql = "SELECT bookname, bookautor, status FROM bookdata";
        Book book;
        List<Book> listOfBooks =  new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                book = new Book();
                book.setName(rs.getString("bookname"));
                book.setAuthor(rs.getString("bookautor"));
                book.setStatus(rs.getString("status"));
                listOfBooks.add(book);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listOfBooks;
    }


    public Book getBook(int bookid) {
        String sql = "SELECT bookname, bookautor FROM bookdata WHERE bookid = ?";
        Book book = new Book();
        book.setBookid(bookid);

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                book.setName(rs.getString("bookname"));
                book.setAuthor(rs.getString("bookautor"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return book;
    }

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws SQLException {

        App app = new App();

        Person person = new Person();
        person.setFirstName("Mati");
        person.setSurname("Kati");
        person.setEmail("kati@mati.ee");

        // app.createUser(person);
        app.checkEmail(person);


        System.out.println(app.loadUser("Jaana@tieto.com"));


        System.out.println(app.getBooks());


        System.out.println(app.getBook(1));
    }









}