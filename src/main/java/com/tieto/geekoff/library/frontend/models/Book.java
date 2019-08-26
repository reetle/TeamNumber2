package com.tieto.geekoff.library.frontend.models;

import java.sql.Date;

public class Book {

    private int bookid;
    private String name;
    private String author;
    private String status;
    private String review;
    private String code;
    private Date startdate;
    private Date enddate;
    private String genre;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ", review='" + review + '\'' +
                ", code='" + code + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", genre='" + genre + '\'' +
                '}';
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public void setGenre(String genre) {this.genre = genre;}

    public String getGenre() {return genre; }
}
