package com.tieto.geekoff.library.frontend.models;



import java.util.List;

public class Person {


   private int id;
   private String firstName;
   private String surname;
   private String email;
   private String role;
   private String image;

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

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public void setImage (String image){ this.image = image;}

   public String getImage() { return image; }

   @Override
   public String toString() {
      return "Person{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", surname='" + surname + '\'' +
              ", email='" + email + '\'' +
              ", role='" + role + '\'' +
              ", image ='" + image + '\'' +


              '}';
   }

   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public List<Book> getBorrowedBooks() {
      return borrowedBooks;
   }

   public void setBorrowedBooks(List<Book> borrowedBooks) {
      this.borrowedBooks = borrowedBooks;
   }


}




