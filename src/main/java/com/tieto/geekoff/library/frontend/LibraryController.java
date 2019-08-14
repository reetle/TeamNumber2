package com.tieto.geekoff.library.frontend;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.tieto.geekoff.library.dao.App;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import com.tieto.geekoff.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class LibraryController {

   private Book newBook;
   private static Person person2;

   @Autowired
   private PersonService personService;

   @RequestMapping(value="person/profile", method = RequestMethod.GET)
   public ModelAndView showProfile(@ModelAttribute("person")Person model) {
      model = person2;
      return new ModelAndView("showProfile", "person", model);
   }


   @RequestMapping(value="person/login", method = RequestMethod.GET)
   public ModelAndView loginPerson(@ModelAttribute("person")Person model) {
      // Person model = loadFromDao();
      return new ModelAndView("showLogin");
   }

   @RequestMapping(value="person/lend", method = RequestMethod.GET)
   public ModelAndView lendBook(@ModelAttribute("book")Book model) {

      return new ModelAndView("lendBooksFront", "book", model);
   }

   @RequestMapping(value="person/load", method = RequestMethod.POST)
   public ModelAndView loadPerson(@ModelAttribute("person")Person model) {

      App app = new App();
      Person person = app.loadUser(model.getEmail());
      person2 = person;
      System.out.println(person2);



      return new ModelAndView("showProfile", "person", person);
   }

   @RequestMapping(value="library/book_confirmation", method = RequestMethod.POST)
   public ModelAndView bookConfirmation(@ModelAttribute("book")Book book) {

      App app = new App();

      book = app.getBook(book.getBookid());
      System.out.println(app.getBook(book.getBookid()));

      app.addBookToPerson(person2, book);
      return new ModelAndView("lendBooksConfirmation", "book", book);
   }


   @RequestMapping(value="library/books")
   public ModelAndView availableBooks(ModelAndView model) {
      App app = new App();
      List<Book> books = app.getBooks();
      model.addObject("books", books);
      model.setViewName("books");

      return model;
   }

   @RequestMapping(value="person/borrowed_books")
   public ModelAndView borrowedBooks(ModelAndView model) {
      App app = new App();
      List<Book> books = app.getBorrowedBooks(person2);
      model.addObject("books", books);
      model.setViewName("borrowedBooks");

      return model;
   }


   /*
   private Person loadFromDao() {
      Person person = new Person();
      person.setFirstName(personService.loadPerson(person));
      person.setSurname(personService.loadPerson(person));
      return person;
   }

    */

   @RequestMapping(value = "person/new", method = RequestMethod.GET)
   public ModelAndView createNewPerson(@ModelAttribute("person")Person model) {


      return new ModelAndView("newPerson", "person", new Person());
   }

   @RequestMapping(value="person/save", method = RequestMethod.POST)
   public ModelAndView savePerson(@ModelAttribute("person")Person model) {
      // personService.savePerson(model);
      App app = new App();
      try {
         if (app.createUser(model)) {
            return new ModelAndView("showProfile", "person", model);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return new ModelAndView("newPerson", "person", new Person());
   }

}
