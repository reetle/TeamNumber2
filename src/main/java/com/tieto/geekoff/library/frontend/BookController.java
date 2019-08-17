package com.tieto.geekoff.library.frontend;

import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "person/lend", method = RequestMethod.GET)
    public ModelAndView lendBook(@ModelAttribute("book") Book model) {

        return new ModelAndView("lendBooksFront", "book", model);
    }

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
}
