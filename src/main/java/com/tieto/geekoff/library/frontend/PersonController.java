package com.tieto.geekoff.library.frontend;

import com.tieto.geekoff.library.dao.impl.BookValidator;
import com.tieto.geekoff.library.dao.impl.PersonValidator;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import com.tieto.geekoff.library.service.BookService;
import com.tieto.geekoff.library.service.LibraryService;
import com.tieto.geekoff.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@SessionAttributes({"person", "book"})
public class PersonController {

    @Autowired
    private PersonValidator personValidator;

    @Autowired
    private BookValidator bookValidator;

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

    @ModelAttribute("book")
    public Book createBookModel() {
        return new Book();
    }

    public Person getPerson2() {
        return person2;
    }

    @RequestMapping(value="person/login", method = RequestMethod.GET)
    public ModelAndView loginPerson(@ModelAttribute("person")Person model) {
        return new ModelAndView("showLogin");
    }

    @RequestMapping(value = "person/login", method = RequestMethod.POST)
    public String userLoginF(@ModelAttribute("person")Person person, BindingResult bindingResult, Model model, SessionStatus status) {

        personValidator.validate(person, bindingResult);
        Pattern pattern = Pattern.compile("[a-zA-Z]+[.][a-zA-Z]+@\\btieto\\b[.]\\bcom\\b",
                Pattern.CASE_INSENSITIVE);

        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error.getCode());
                if (error.getCode().equals("email.exists")) {
                    person2 = personService.loadUser(person.getEmail());
                    status.setComplete();
                    return "redirect:/app/profile";
                }
            }

        }
        if (pattern.matcher(person.getEmail()).matches() && !(personService.checkAccountAlreadyExist(person.getEmail()))) {
            bindingResult.rejectValue("email", "account.notRegistered", "No registered account with this email!");
            return "showLogin";
        }

        return "showLogin";


    }


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        person2 = personService.loadUser(person2.getEmail());
        return new ModelAndView("showProfile", "person", person2);
    }

    /*
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String userProfile(@ModelAttribute("person")@Validated Person person, BindingResult bindingResult, Model model) {


        try {
            personService.createUser(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        person2 = personService.loadUser(person.getEmail());
        person2.setBorrowedBooks(personService.getBorrowedBooks(person2));
        model.addAttribute("person", person2);


        return "showProfile";
    }

     */


    @RequestMapping(value = "/addPerson", method = RequestMethod.GET)
    public ModelAndView person() {

        return new ModelAndView("newPerson", "person", new Person());
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person person,
                             BindingResult bindingResult, Model model, SessionStatus status) {

        personValidator.validate(person, bindingResult);

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
        status.setComplete();
        return "redirect:/app/profile";
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
    public String returnBook(@ModelAttribute("book") Book book, Model model, BindingResult bindingResult, SessionStatus status) {

        bookValidator.validate(book, bindingResult);


        if (bindingResult.hasErrors()) {
            
            return "returnBook";
        }

        if (!bookService.doIHaveThisBook(book.getCode(), person2)) {
            bindingResult.rejectValue("code", "book.notYours", "You haven't lended that book!");
            return "returnBook";
        }

        book = bookService.getBook(book.getCode());
        personService.removeBookFromPerson(person2, book);
        libraryService.bookIsAvailable(book.getBookid());
        model.addAttribute("person", person2);
        status.setComplete();
        return "redirect:/app/profile";



        /*
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error);
                if (error.getCode().equals("code.exists")) {
                    book = bookService.getBook(book.getCode());
                    personService.removeBookFromPerson(person2, book);
                    libraryService.bookIsAvailable(book.getBookid());
                    model.addAttribute("person", person2);
                    status.setComplete();
                    return "redirect:/app/profile";
                }
            }
        }
        return "returnBook";

         */


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

    @RequestMapping(value = "history")
    public String personLendedBooksHistory(@ModelAttribute("book")Book book, Model model) {
        List<Book> books = personService.getLendingHistory(person2);
        model.addAttribute("books", books);
        model.addAttribute("person", person2);
        return "personLendingHistory";
    }

    @RequestMapping(value = "person/lend")
    public String lendBook(@ModelAttribute("book") Book book, Model model) {


        model.addAttribute("person", person2);

        return "lendBooksFront";
    }

    @RequestMapping(value = "person/lend", method = RequestMethod.POST)
    public String bookConfirmation(@ModelAttribute("book") Book book, Model model, BindingResult bindingResult, SessionStatus status) {

        bookValidator.validate(book, bindingResult);


        if (bindingResult.hasErrors()) {
            return "lendBooksFront";
        }

        if (!bookService.isBookAvailable(book.getCode())) {
            bindingResult.rejectValue("code", "code.exists", "Book is already booked!");
            return "lendBooksFront";
        }

        book = bookService.getBook(book.getCode());
        book2 = book;
        System.out.println(book);
        model.addAttribute("book", book);
        model.addAttribute("person", person2);
        status.setComplete();
        return "lendBooksConfirmation";



        /*
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                if (error.getCode().equals("book.notYours")) {
                    book = bookService.getBook(book.getCode());
                    book2 = book;
                    System.out.println(book);
                    model.addAttribute("book", book);
                    model.addAttribute("person", person2);
                    status.setComplete();
                    return "lendBooksConfirmation";
                }
            }

        }
        return "lendBooksFront";

         */


    }



}
