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
}
