package com.tieto.geekoff.library.dao.impl;

import com.tieto.geekoff.library.dao.App;
import com.tieto.geekoff.library.dao.BookDao;
import com.tieto.geekoff.library.frontend.models.Book;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BookDaoImpl implements BookDao {

    App app = new App();


    public Book getBook(int id) {
        String sql = "SELECT bookid, bookname, bookautor, status, review, code FROM bookdata WHERE bookid = ?";
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
                book.setCode(rs.getInt("code"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return book;
    }


    public void saveBook(Book book) {
        String sql = "INSERT INTO bookdata(bookname,bookautor,status,code) values(?,?,?,?)";


        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3,"available");
            pstmt.setString(3,book.getCode());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

    public void updateBook (Book book){
        String sql = "UPDATE bookdata SET bookname = ?, bookautor = ?";

        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean isBookAvailable(int code) {
        String sql = "SELECT bookname FROM bookdata WHERE bookid = ? AND status = ?";
        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, code);
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

    public boolean isBookInDatabase(int code) {
        String sql = "SELECT bookname FROM bookdata WHERE bookid = ?";
        try (Connection conn = app.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, code);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        return false;
    }
}

