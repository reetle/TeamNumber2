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

    public Book getBook(int bookid) {
        String sql = "SELECT bookname, bookautor FROM bookdata WHERE bookid = ?";
        Book book = new Book();
        book.setBookid(bookid);

        try (Connection conn = app.connect();
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
}
