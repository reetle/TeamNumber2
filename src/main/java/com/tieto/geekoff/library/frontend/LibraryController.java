package com.tieto.geekoff.library.frontend;

//import com.github.sarxos.webcam.Webcam;
//import com.github.sarxos.webcam.WebcamPanel;
//import com.github.sarxos.webcam.WebcamResolution;
import com.tieto.geekoff.library.dao.App;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import com.tieto.geekoff.library.service.BookService;
import com.tieto.geekoff.library.service.LibraryService;
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

   private static Person person2;

   @Autowired
   private BookService bookService;

   @Autowired
   private LibraryService libraryService;

   @Autowired
   private PersonService personService;



   /*
   @RequestMapping(value="library/books")
   public ModelAndView availableBooks(ModelAndView model) {

      List<Book> books = libraryService.getBooks();
      model.addObject("books", books);


      //THIS IS A HACK, Person should come from DB after login
      Person admin = new Person();
      admin.setRole("admin");


      model.addObject("person", admin);
      model.setViewName("books");

      return model;
   }

    */






   /*
   private Person loadFromDao() {
      Person person = new Person();
      person.setFirstName(personService.loadPerson(person));
      person.setSurname(personService.loadPerson(person));
      return person;
   }

    */



}
