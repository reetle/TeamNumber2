package com.tieto.geekoff.library.frontend;

import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import com.tieto.geekoff.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private PersonController personController;



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

        model.addAttribute("book", book);
        return "bookEdit";
    }



    @RequestMapping(value = "saveBookEdit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("book") Book book, Model model){
        model.addAttribute("book", book);
        bookService.updateBook(book);
        return "redirect:/app/library/books";
    }

    @RequestMapping(value = "/book/delete/{bookid}", method = RequestMethod.GET)
    public String deleteBook (@PathVariable int bookid, Model model){
        bookService.deleteBook(bookid);
        // model.addAttribute("book");
        return "redirect:/app/library/books";
    }



}
