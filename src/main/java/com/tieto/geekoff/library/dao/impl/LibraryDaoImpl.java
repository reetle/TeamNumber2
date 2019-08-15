package com.tieto.geekoff.library.dao.impl;

import com.tieto.geekoff.library.dao.App;
import com.tieto.geekoff.library.dao.LibraryDao;
import com.tieto.geekoff.library.frontend.models.Book;
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
        String sql = "SELECT bookname, bookautor, status FROM bookdata";
        Book book;
        List<Book> listOfBooks =  new ArrayList<>();

        try (Connection conn = app.connect();
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
}
