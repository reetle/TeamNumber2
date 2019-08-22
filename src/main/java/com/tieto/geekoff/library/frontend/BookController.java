package com.tieto.geekoff.library.frontend;

import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import com.tieto.geekoff.library.service.BookService;
import com.tieto.geekoff.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;





/*
    @RequestMapping(value = "book/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editBook(@PathVariable("id") String bookId, ModelAndView model) {

        model.addObject("book", bookService.getBook(Integer.valueOf(bookId)));
        model.setViewName("bookEdit");
        return model;
    }

    @RequestMapping(value = "book/save", method = RequestMethod.POST)
    public RedirectView saveBook(@ModelAttribute("book") Book book, ModelAndView model) {
        bookService.saveBook(book);
        return new RedirectView("/app/library/books");
    }
*/


    @RequestMapping(value = "/book/new", method = RequestMethod.GET)
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "addNewBook";
    }

    @RequestMapping(value= "/saveNewBook", method = RequestMethod.POST)
    public String save(@ModelAttribute("book") Book book){

        bookService.saveBook(book);
        return "redirect:/app/library/books";
    }

    @RequestMapping(value = "/book/edit/{bookid}", method = RequestMethod.GET)
    public String editBook(@PathVariable int bookid, Model model){
        Book book=bookService.getBook(bookid);
        System.out.println(book);
        model.addAttribute("book", book);
        return "bookEdit";
    }

    @RequestMapping(value = "saveBookEdit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("book") Book book){
        bookService.updateBook(book);
        return "redirect:/app/library/books";
    }

}
