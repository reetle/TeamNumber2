package com.tieto.geekoff.library.frontend.models;

import java.util.List;

public class Person {

   private String firstName;
   private String surname;
   private String email;
   private long id;
   private List<Book> borrowedBooks;


   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   public String getSurname() {
      return this.surname;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }


   public List<Book> getBorrowedBooks() {
      return borrowedBooks;
   }

   public void setBorrowedBooks(List<Book> borrowedBooks) {
      this.borrowedBooks = borrowedBooks;
   }


   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }
}
