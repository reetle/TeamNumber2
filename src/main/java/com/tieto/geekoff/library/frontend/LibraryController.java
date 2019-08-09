package com.tieto.geekoff.library.frontend;

import com.tieto.geekoff.library.frontend.models.Person;
import com.tieto.geekoff.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LibraryController {

   @Autowired
   private PersonService personService;

   @RequestMapping(value="person/load", method = RequestMethod.GET)
   public ModelAndView loadPersons() {
      Person model = loadFromDao();
      return new ModelAndView("newPerson", "person", model);
   }

   @RequestMapping(value="person/save", method = RequestMethod.POST)
   public ModelAndView savePerson(@ModelAttribute("person")Person model) {
      personService.savePerson(model.getFirstName());
      return new ModelAndView("newPerson", "person", loadFromDao());
   }

   private Person loadFromDao() {
      Person person = new Person();
      person.setFirstName(personService.loadPerson());
      return person;
   }

}
