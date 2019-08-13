package com.tieto.geekoff.library.frontend;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.tieto.geekoff.library.dao.App;
import com.tieto.geekoff.library.frontend.models.Person;
import com.tieto.geekoff.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller
public class LibraryController {

   @Autowired
   private PersonService personService;

   /*
   @RequestMapping(value="person/load", method = RequestMethod.GET)
   public ModelAndView loadPersons() {
      Person model = loadFromDao();
      return new ModelAndView("newPerson", "person", model);
   }
   */
   @RequestMapping(value="person/load", method = RequestMethod.GET)
   public ModelAndView loadPersons() {
      Person model = loadFromDao();



      return new ModelAndView("showProfile", "person", model);
   }

   /*
   @RequestMapping(value="person/save", method = RequestMethod.POST)
   public ModelAndView savePerson(@ModelAttribute("person")Person model) {
      personService.savePerson(model.getFirstName());
      return new ModelAndView("newPerson", "person", loadFromDao());
   }

    */

   private Person loadFromDao() {
      Person person = new Person();
      person.setFirstName(personService.loadPerson(person));
      person.setSurname(personService.loadPerson(person));
      return person;
   }

   @RequestMapping(value = "person/new", method = RequestMethod.GET)
   public ModelAndView createNewPerson(@ModelAttribute("person")Person model) {


      return new ModelAndView("newPerson", "person", new Person());
   }

   @RequestMapping(value="person/save", method = RequestMethod.POST)
   public ModelAndView savePerson(@ModelAttribute("person")Person model) {
      // personService.savePerson(model);
      App app = new App();
      try {

         app.createUser(model);
      } catch (SQLException e) {
         e.printStackTrace();
      }


      return new ModelAndView("showProfile", "person", model);
   }


   public static void takeProfilePicture() {
      // get default webcam and open it
      Webcam webcam = Webcam.getDefault();
      webcam.open();
      try {
         ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }


}
