package com.tieto.geekoff.library.dao.impl;

import com.tieto.geekoff.library.dao.App;
import com.tieto.geekoff.library.dao.LibraryDao;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LibraryDaoImpl implements LibraryDao {

    App app = new App();


    public List<Book> getBooks() {
        String sql = "SELECT bookid, bookname, bookautor, status, review, code, genre FROM bookdata";
        Book book;
        List<Book> listOfBooks =  new ArrayList<>();

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                book = new Book();
                book.setBookid(rs.getInt("bookid"));
                book.setName(rs.getString("bookname"));
                book.setAuthor(rs.getString("bookautor"));
                book.setStatus(rs.getString("status"));
                book.setReview(rs.getString("review"));
                book.setCode(rs.getString("code"));
                book.setGenre(rs.getString("genre"));
                listOfBooks.add(book);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listOfBooks;
    }


    public void bookIsNotAvailable(Person person, Book book) {
        String sql = "UPDATE bookdata SET status=? WHERE bookid = ?";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String statusUpdated = "Booked by " + person.getFirstName() + " " + person.getSurname() + " until " + book.getEnddate() + ".";
            pstmt.setString(1, statusUpdated);
            pstmt.setInt(2, book.getBookid());
            pstmt.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void bookIsAvailable(int id) {
        String sql = "UPDATE bookdata SET status=? WHERE bookid = ?";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "available");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Book> getAllLendedBooks() {
        return null;
    }
}
