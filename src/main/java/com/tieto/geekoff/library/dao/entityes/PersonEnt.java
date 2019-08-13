package com.tieto.geekoff.library.dao.entityes;

public class PersonEnt {

   private String firstName;
   private String surname;
   private String email;


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


}
