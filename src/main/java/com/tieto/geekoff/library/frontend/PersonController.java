package com.tieto.geekoff.library.frontend;

import com.tieto.geekoff.library.dao.App;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import com.tieto.geekoff.library.service.BookService;
import com.tieto.geekoff.library.service.LibraryService;
import com.tieto.geekoff.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.sql.SQLException;
import java.util.List;

@Controller
public class PersonController {

    @Autowired
    @Qualifier("personValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    private static Person person2;

    @Autowired
    private PersonService personService;

    @Autowired
    private BookService bookService;

    @Autowired
    private LibraryService libraryService;

    @ModelAttribute("person")
    public Person createPersonModel() {
        return new Person();
    }

    @RequestMapping(value="person/profile", method = RequestMethod.GET)
    public ModelAndView showProfile(@ModelAttribute("person")Person model, ModelAndView modelAndView) {
        model = person2;

        modelAndView.addObject("person", model);
        List<Book> books = personService.getBorrowedBooks(person2);
        System.out.println(books);
        modelAndView.addObject("books", books);

        modelAndView.setViewName("showProfile");
        return modelAndView;
        // return new ModelAndView("showProfile", "person", model);
    }


    @RequestMapping(value="person/login", method = RequestMethod.GET)
    public ModelAndView loginPerson(@ModelAttribute("person")Person model) {

        return new ModelAndView("showLogin");
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        person2 = personService.loadUser(person2.getEmail());
        return new ModelAndView("showProfile", "person", person2);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String userProfile(@ModelAttribute("person")@Validated Person person, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "showLogin";
        }


        person2 = personService.loadUser(person.getEmail());
        System.out.println(person2);
        person2.setBorrowedBooks(personService.getBorrowedBooks(person2));
        model.addAttribute("person", person2);

        if (person2.getRole() == null) {
            return "showLogin";
        }

        if (personService.isAdmin(person2)) {
            return "adminView";
        }

        return "showProfile";
    }

    @RequestMapping(value="person/borrowed_books")
    public ModelAndView borrowedBooks(ModelAndView model) {
        App app = new App();
        List<Book> books = personService.getBorrowedBooks(person2);
        model.addObject("books", books);

        model.setViewName("borrowedBooks");

        return model;
    }



    @RequestMapping(value = "/addPerson", method = RequestMethod.GET)
    public ModelAndView person() {
        return new ModelAndView("newPerson", "person", new Person());
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") @Validated Person person,
                             BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "newPerson";
        }

        try {
            if (personService.createUser(person)) {
                person2 = personService.loadUser(person.getEmail());
                model.addAttribute("person", person2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "showProfile";
    }

    @RequestMapping(value="person/save", method = RequestMethod.POST)
    public ModelAndView savePerson(@ModelAttribute("person") @Validated Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("YEEE");
            return new ModelAndView("newPerson", "person", new Person());
        }
        System.out.println("NOOO");

        try {
            if (personService.createUser(person)) {
                person2 = personService.loadUser(person.getEmail());
                if (personService.isAdmin(person2)) {
                    System.out.println("ADMIN");
                    return new ModelAndView("adminView", "person", person);
                }
                return new ModelAndView("showProfile", "person", person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ModelAndView("newPerson", "person", new Person());
    }

    @RequestMapping(value = "/library/book_confirmation", method = RequestMethod.POST)
    public String bookConfirmation(@ModelAttribute("book")Book book, Model model) {
        book = bookService.getBook(book.getBookid());
        System.out.println(book);
        personService.addBookToPerson(person2, book);
        libraryService.bookIsNotAvailable(book.getBookid());
        model.addAttribute("book", book);
        return "lendBooksConfirmation";

    }

    @RequestMapping(value="book/return", method = RequestMethod.POST)
    public ModelAndView returnBook(@ModelAttribute("book") Book book) {
        book = bookService.getBook(book.getBookid());
        personService.removeBookFromPerson(person2, book);
        libraryService.bookIsAvailable(book.getBookid());

        return new ModelAndView("returnBook", "book", book);
    }
}
