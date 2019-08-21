package com.tieto.geekoff.library.frontend;

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
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.sql.SQLException;
import java.util.Arrays;
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
    private static Book book2;

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

    @RequestMapping(value="person/login", method = RequestMethod.GET)
    public ModelAndView loginPerson(@ModelAttribute("person")Person model) {

        return new ModelAndView("showLogin");
    }

    @RequestMapping(value = "person/login", method = RequestMethod.POST)
    public String userLoginF(@ModelAttribute("person")@Validated Person person, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error.getCode());
                if (error.getCode().equals("email.exists")) {
                    person2 = personService.loadUser(person.getEmail());
                    return "redirect:/app/profile";
                }
            }
            return "showLogin";
        }
        return "showLogin";


    }


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        System.out.println(person2);
        person2 = personService.loadUser(person2.getEmail());
        System.out.println(person2);
        return new ModelAndView("showProfile", "person", person2);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String userProfile(@ModelAttribute("person")@Validated Person person, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
            return "showLogin";
        }

        try {
            personService.createUser(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        person2 = personService.loadUser(person.getEmail());
        person2.setBorrowedBooks(personService.getBorrowedBooks(person2));
        model.addAttribute("person", person2);

        if (person2.getRole() == null) {
            return "showLogin";
        }

        return "showProfile";
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

        return "redirect:/app/profile";
    }

    @RequestMapping(value = "/library/book_confirmation", method = RequestMethod.POST)
    public String bookConfirmation(@ModelAttribute("book")Book book, Model model) {
        book = bookService.getBook(book.getBookid());
        book2 = book;
        System.out.println(book);
        model.addAttribute("book", book);
        model.addAttribute("person", person2);
        return "lendBooksConfirmation";

    }

    @RequestMapping(value = "/library/book_confirm_yes", method = RequestMethod.GET)
    public String bookYes(@ModelAttribute("book")Book book, Model model) {
        personService.addBookToPerson(person2, book2);
        System.out.println(book2);
        libraryService.bookIsNotAvailable(book2.getBookid());
        model.addAttribute("book", book2);
        model.addAttribute("person", person2);
        return "redirect:/app/profile";
    }

    @RequestMapping(value = "/library/book_confirm_no", method = RequestMethod.GET)
    public String bookNo(@ModelAttribute("book")Book book, Model model) {

        return "redirect:/app/person/lend";
    }



    @RequestMapping(value="book/return", method = RequestMethod.POST)
    public String returnBook(@ModelAttribute("book") Book book, Model model) {
        book = bookService.getBook(book.getBookid());
        personService.removeBookFromPerson(person2, book);
        libraryService.bookIsAvailable(book.getBookid());
        model.addAttribute("person", person2);
        return "returnBook";
    }

    @RequestMapping(value = "book/return", method = RequestMethod.GET)
    public String returnBooks(@ModelAttribute("book") Book book, Model model) {

        model.addAttribute("person", person2);
        return "returnBook";
    }

    @RequestMapping(value = "library/books")
    public String allBooks(@ModelAttribute("book")Book book, Model model) {
        List<Book> books = libraryService.getBooks();
        model.addAttribute("books", books);
        model.addAttribute("person", person2);
        return "books";
    }

    @RequestMapping(value = "person/lend")
    public String lendBook(@ModelAttribute("book") Book book, Model model) {

        model.addAttribute("person", person2);
        return "lendBooksFront";
    }



}
