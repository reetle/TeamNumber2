package com.tieto.geekoff.library.frontend;

import com.tieto.geekoff.library.dao.HttpPostConnection;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@SessionAttributes({"person", "book"})
public class PersonController {

    private final int LENDING_DURATION = 20;

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
    public String userLoginF(@ModelAttribute("person")Person person, BindingResult bindingResult, Model model, SessionStatus status) throws IOException {

        personValidator.validate(person, bindingResult);

        HttpPostConnection post = new HttpPostConnection();



        Pattern pattern = Pattern.compile("[a-zA-Z]+[.][a-zA-Z]+@\\btieto\\b[.]\\bcom\\b", Pattern.CASE_INSENSITIVE);

        // Siia lisasin errori kinnipüüdmise, et ilma emailita hakata kasutajat tuvastama.
        if (bindingResult.hasErrors()) {
            if (person.getImage().length() == 0) {
                bindingResult.rejectValue("image", "image.empty", "Make a Snap Shot!");
                return "showLogin";
            }
            if (person.getEmail().length() == 0) {
                List<Person> personList = personService.getPersons();
                for (Person user : personList) {
                    // user on andmebaasist, person on login tulev väärtus
                    if (post.faceRecognise(personService.getPersonImageString(user.getEmail()), person.getImage().substring(22))) {
                        person2 = personService.loadUser(user.getEmail());
                        status.setComplete();
                        return "redirect:/app/profile";
                    }
                }
                bindingResult.rejectValue("image", "faceRecognise.failed", "Face recognition failed!");
                System.out.println("seda kasutajat pole meil");
                return "showLogin";

            }
            return "showLogin";

        }
        if (pattern.matcher(person.getEmail()).matches() && !(personService.checkAccountAlreadyExist(person.getEmail()))) {
            bindingResult.rejectValue("email", "account.notRegistered", "No registered account with this email!");
            return "showLogin";
        }


        if (!(post.faceRecognise(personService.getPersonImageString(person.getEmail()), person.getImage().substring(22)))) {
            bindingResult.rejectValue("image", "faceRecognise.failed", "Face recognition failed!");
            System.out.println("face");
            return "showLogin";
        }
        person2 = personService.loadUser(person.getEmail());
        status.setComplete();
        return "redirect:/app/profile";


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
        System.out.println(person.getImage());

        if (personService.checkAccountAlreadyExist(person.getEmail())) {
            bindingResult.rejectValue("email", "email.exists", "The username already exists. Please use a different username.");
            return "newPerson";
        }

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

    @RequestMapping(value = "allHistory")
    public String allLendingHistory(@ModelAttribute("book")Book book, Model model) {
        Map<Person, List<Book>> personListMap = personService.getAllHistory();
        model.addAttribute("map", personListMap);
        model.addAttribute("person", person2);
        return "adminAllHistory";
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
    }

    @RequestMapping(value = "/library/book_confirm_yes", method = RequestMethod.GET)
    public String bookYes(@ModelAttribute("book")Book book, Model model) {
        personService.addBookToPerson(person2, book2);
        book2.setStartdate(Date.valueOf(LocalDate.now()));
        book2.setEnddate(Date.valueOf(LocalDate.now().plusDays(LENDING_DURATION)));
        System.out.println(book2);
        libraryService.bookIsNotAvailable(person2, book2);
        model.addAttribute("book", book2);
        model.addAttribute("person", person2);
        return "redirect:/app/profile";
    }

    @RequestMapping(value = "/library/book_confirm_no", method = RequestMethod.GET)
    public String bookNo(@ModelAttribute("book")Book book, Model model) {

        return "redirect:/app/person/lend";
    }

//========Maris ======================================

    @RequestMapping(value = "/allPersons")
    public String allPersons(@ModelAttribute("person")Person person, Model model) {//Kas @ModelAttribute on vajalik??
        List<Person> persons = personService.getPersons(); //Ok
        model.addAttribute("persons", persons );//1. "persons" on jsp jaoks, 2. on lok muutuja
        model.addAttribute("person", person2); //person2 on aktiivne kasutaja?
        return "persons"; //failinimi
    }

    @RequestMapping(value = "/person/edit/{id}", method = RequestMethod.GET)
    public String editPerson(@PathVariable int id, Model model){
        Person person = personService.getPerson(id);
        model.addAttribute("person2", person2);
        model.addAttribute("person", person);
        return "personEdit";
    }

    @RequestMapping(value = "/savePersonEdit", method = RequestMethod.POST)
    public String savePersonEdit(@ModelAttribute("person") Person person, Model model){
        model.addAttribute("person", person);
        personService.updatePerson(person);
        return "redirect:/app/allPersons";
    }

    @RequestMapping(value = "/person/delete_ask_confirmation/{id}", method = RequestMethod.GET)
    public String deletePersonAskConfirmation(@PathVariable int id, Model model){
        Person person = personService.getPerson(id);
        model.addAttribute("personToDelete", person);
        model.addAttribute("person", person2);
        return "deletePersonConfirmation";
    }

    @RequestMapping(value = "/person/delete_confirm_yes/{id}", method = RequestMethod.GET)
    public String deletePersonFinally(@PathVariable int id){
        personService.deletePerson(id);
        return "redirect:/app/allPersons";
    }

    @RequestMapping(value = "/person/delete_confirm_no", method = RequestMethod.GET)
    public String deletePersonInterrupt(){
        return "redirect:/app/allPersons";
    }

}
