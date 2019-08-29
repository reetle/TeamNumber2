package com.tieto.geekoff.library.dao.impl;

import com.tieto.geekoff.library.dao.App;
import com.tieto.geekoff.library.dao.PersonDao;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import com.tieto.geekoff.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonDaoImpl implements PersonDao {


    @Autowired
    private LibraryService libraryService;

    private final String TIETO_DOMAIN = "tieto.com";
    private final int LENDING_DURATION = 20;

    App app = new App();

    public boolean checkEmail(Person person) {
        String email = person.getEmail().substring(person.getEmail().indexOf("@") + 1);
        return email.equals(TIETO_DOMAIN);
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
                + " email,"
                + " image"
                + ") VALUES ("
                + "?, ?, ?,?)";

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
                statement.setString(4, person.getImage().substring(22));



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
        String sql = "SELECT bookid, startdate, status FROM orderedbookshistory WHERE personid = ?";
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
                    book.setStartdate(rs.getDate("startdate"));
                    book.setHistoryStatus(rs.getString("status"));
                    books.add(book);
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return books;
    }

    public Map<Person, List<Book>> getAllHistory() {
        List<Person> personList = libraryService.getAllPersons();
        Map<Person, List<Book>> personBookMap = new HashMap<>();

        for (Person person : personList) {
            if (getLendingHistory(person).size() > 0) {
                personBookMap.put(person, getLendingHistory(person));
            }

        }

        return personBookMap;
    }


    public void addBookToPerson(Person person, Book book) {
        String sql = "INSERT INTO orderedbooks (personid, bookid, startdate, enddate) VALUES (?,?, ?,?)";
        String sqlHistory = "INSERT INTO orderedbookshistory (personid, bookid, startdate, status) VALUES (?,?,?,?)";

        LocalDate now = LocalDate.now();
        LocalDate end = now.plusDays(LENDING_DURATION);

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            PreparedStatement statement = conn.prepareStatement(sqlHistory);
            statement.setInt(1, person.getId());
            statement.setInt(2, book.getBookid());
            statement.setDate(3, Date.valueOf(now));
            statement.setString(4, "holding");
            statement.executeUpdate();

            pstmt.setInt(1, person.getId());
            pstmt.setInt(2, book.getBookid());
            pstmt.setDate(3, Date.valueOf(now));
            pstmt.setDate(4, Date.valueOf(end));
            pstmt.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void removeBookFromPerson(Person person, Book book) {
        String sql = "DELETE FROM orderedbooks WHERE bookid = ? AND personid = ?";
        String returned = "UPDATE orderedbookshistory SET status = ? WHERE bookid = ? AND personid = ? AND status = ?";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            PreparedStatement statement = conn.prepareStatement(returned);
            statement.setString(1, "returned");
            statement.setInt(2, book.getBookid());
            statement.setInt(3, person.getId());
            statement.setString(4, "holding");
            statement.executeUpdate();

            pstmt.setInt(1, book.getBookid());
            pstmt.setInt(2, person.getId());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public List<Book> getBorrowedBooks(Person person) {
        List<Book> books = new ArrayList<>();

        String sql = "SELECT bookid, startdate, enddate FROM orderedbooks WHERE personid = ?";
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
                    book.setStartdate(rs.getDate("startdate"));
                    book.setEnddate(rs.getDate("enddate"));
                    books.add(book);
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return books;
    }

    //==========Maris ==========================
    public List<Person> getPersons() {
        String sql = "SELECT personid, firstname, lastname, email, role, image FROM persondata";
        Person person;
        List<Person> listOfPersons =  new ArrayList<>();

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                person = new Person();
                person.setId(rs.getInt("personid"));
                person.setFirstName(rs.getString("firstname"));
                person.setSurname(rs.getString("lastname"));
                person.setEmail(rs.getString("email"));
                person.setRole(rs.getString("role"));
                person.setImage(rs.getString("image"));
                listOfPersons.add(person);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listOfPersons;
    }

    public void updatePerson (Person person){
        String sql = "UPDATE persondata SET firstname=?, lastname=?, email=?, role=? WHERE personid=?";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, person.getFirstName());
            pstmt.setString(2, person.getSurname());
            pstmt.setString(3, person.getEmail());
            pstmt.setString(4, person.getRole());
            pstmt.setInt(5, person.getId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public Person getPerson(int id) {
        String sql = "SELECT personid, firstname, lastname, email, role, image FROM persondata WHERE personid = ?";
        Person person = new Person();

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                person.setId(rs.getInt("personid"));
                person.setFirstName(rs.getString("firstname"));
                person.setSurname(rs.getString("lastname"));
                person.setEmail(rs.getString("email"));
                person.setRole(rs.getString("role"));
                person.setImage(rs.getString("image"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return person;
    }

    public void deletePerson (int personid){
        String sql ="DELETE FROM persondata WHERE personid=?";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, personid);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getPersonImageString(String email) {
        String sql = "SELECT image FROM persondata WHERE email = ?";


        try (Connection conn = app.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {

                // System.out.println(rs.getString("image"));
                return rs.getString("image");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }



}