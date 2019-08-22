package com.tieto.geekoff.library.dao.impl;

import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {


    @Autowired
    private BookService bookService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code","code.required", "Barcode field is missing");

        Book book = (Book) target;

        if (!(book.getCode().equals(""))) {
            if (!bookService.isBookInDatabase(book.getCode())) {
                errors.rejectValue("code", "error.NotInDatabase", "There is not such a book in library.");

            } else if (!bookService.isBookAvailable(book.getCode())) {
                errors.rejectValue("code", "code.exists", "Book is already booked!");
            }
        }






    }


}




