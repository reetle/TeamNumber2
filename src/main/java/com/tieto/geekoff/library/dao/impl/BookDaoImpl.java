package com.tieto.geekoff.library.dao.impl;

import com.tieto.geekoff.library.dao.App;
import com.tieto.geekoff.library.dao.BookDao;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    App app = new App();


    public Book getBook(String code) {
        String sql = "SELECT bookid, bookname, bookautor, status, review, code, genre FROM bookdata WHERE code = ?";
        Book book = new Book();


        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                book.setBookid(rs.getInt("bookid"));
                book.setName(rs.getString("bookname"));
                book.setAuthor(rs.getString("bookautor"));
                book.setStatus(rs.getString("status"));
                book.setReview(rs.getString("review"));
                book.setCode(rs.getString("code"));
                book.setGenre(rs.getString("genre"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return book;
    }


    public Book getBook(int id) {
        String sql = "SELECT bookid, bookname, bookautor, status, review, code, genre FROM bookdata WHERE bookid = ? ORDER BY bookname";
        Book book = new Book();

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                book.setBookid(rs.getInt("bookid"));
                book.setName(rs.getString("bookname"));
                book.setAuthor(rs.getString("bookautor"));
                book.setStatus(rs.getString("status"));
                book.setReview(rs.getString("review"));
                book.setCode(rs.getString("code"));
                book.setGenre(rs.getString("genre"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return book;
    }


    public void saveBook(Book book) {

        String sql = "INSERT INTO bookdata(bookname,bookautor,status, code, genre) values(?,?,?,?,?)";



        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3,"available");

            pstmt.setString(4, book.getCode());
            pstmt.setString(5, book.getGenre());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }




    public void updateBook (Book book){
        String sql = "UPDATE bookdata SET bookname = ?, bookautor = ?, genre = ? WHERE bookid=?";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getGenre());
            pstmt.setInt(4, book.getBookid());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteBook (int bookid){
        String sql ="DELETE FROM bookdata WHERE bookid=?";

        try (Connection conn = app.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookid);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   public boolean isBookAvailable(String code) {
        String sql = "SELECT bookname FROM bookdata WHERE code = ? AND status = ?";
        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, code);
            pstmt.setString(2, "available");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean isBookInDatabase(String code) {
        String sql = "SELECT bookname FROM bookdata WHERE code = ?";
        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, code);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }


    public boolean doIHaveThisBook(String code, Person person) {
        String sqlBookInfo = "SELECT bookid FROM bookdata WHERE code = ?";
        String sql = "SELECT personid FROM orderedbooks WHERE bookid = ? AND personid = ?";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            PreparedStatement statement = conn.prepareStatement(sqlBookInfo);
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                pstmt.setInt(1, resultSet.getInt("bookid"));
                pstmt.setInt(2, person.getId());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }


}

