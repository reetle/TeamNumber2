package com.tieto.geekoff.library.frontend;

import com.tieto.geekoff.library.dao.App;
import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import com.tieto.geekoff.library.service.BookService;
import com.tieto.geekoff.library.service.LibraryService;
import com.tieto.geekoff.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
public class PersonController {

    private Person person2;

    @Autowired
    private PersonService personService;

    @Autowired
    private BookService bookService;

    @Autowired
    private LibraryService libraryService;

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
        // Person model = loadFromDao();
        return new ModelAndView("showLogin");
    }

    @RequestMapping(value="person/load", method = RequestMethod.POST)
    public ModelAndView loadPerson(@ModelAttribute("person")Person model, ModelAndView modelAndView) {

        Person person = personService.loadUser(model.getEmail());
        person2 = person;
        System.out.println(person2);
        modelAndView.addObject("person", person);
        List<Book> books = personService.getBorrowedBooks(person2);
        System.out.println(books);
        modelAndView.addObject("books", books);

        if (personService.isAdmin(person2)) {
            modelAndView.setViewName("adminView");
        } else {
            modelAndView.setViewName("showProfile");
        }




        return modelAndView;
        // return new ModelAndView("showProfile", "person", person);
    }

    @RequestMapping(value="person/borrowed_books")
    public ModelAndView borrowedBooks(ModelAndView model) {
        App app = new App();
        List<Book> books = personService.getBorrowedBooks(person2);
        model.addObject("books", books);

        model.setViewName("borrowedBooks");

        return model;
    }

    @RequestMapping(value = "person/new", method = RequestMethod.GET)
    public ModelAndView createNewPerson(@ModelAttribute("person")Person model) {


        return new ModelAndView("newPerson", "person", new Person());
    }

    @RequestMapping(value="person/save", method = RequestMethod.POST)
    public ModelAndView savePerson(@ModelAttribute("person")Person model) {

        try {
            if (personService.createUser(model)) {
                person2 = personService.loadUser(model.getEmail());
                if (personService.isAdmin(person2)) {
                    System.out.println("ADMIN");
                    return new ModelAndView("adminView", "person", model);
                }
                return new ModelAndView("showProfile", "person", model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ModelAndView("newPerson", "person", new Person());
    }

    @RequestMapping(value="library/book_confirmation", method = RequestMethod.POST)
    public ModelAndView bookConfirmation(@ModelAttribute("book") Book book) {

        App app = new App();

        book = bookService.getBook(book.getBookid());
        personService.addBookToPerson(person2, book);
        libraryService.bookIsNotAvailable(book.getBookid());

        return new ModelAndView("lendBooksConfirmation", "book", book);
    }

    @RequestMapping(value="book/return", method = RequestMethod.POST)
    public ModelAndView returnBook(@ModelAttribute("book") Book book) {
        book = bookService.getBook(book.getBookid());
        personService.removeBookFromPerson(person2, book);
        libraryService.bookIsAvailable(book.getBookid());

        return new ModelAndView("returnBook", "book", book);
    }
}
