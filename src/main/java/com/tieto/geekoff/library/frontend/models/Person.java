package com.tieto.geekoff.library.frontend.models;


public class Person {

   private int id;
   private String firstName;
   private String surname;
   private String email;
   private String role;


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

   @Override
   public String toString() {
      return "Person{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", surname='" + surname + '\'' +
              ", email='" + email + '\'' +
              ", role='" + role + '\'' +
              '}';
   }

   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role;
   }
}
