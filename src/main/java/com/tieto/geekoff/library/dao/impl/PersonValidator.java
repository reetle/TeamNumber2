package com.tieto.geekoff.library.dao.impl;

import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import com.tieto.geekoff.library.service.BookService;
import com.tieto.geekoff.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Pattern;

@Component
public class PersonValidator implements Validator {

    @Autowired
    private PersonService personService;


    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","email.required", "Name field is missing");

        Person person = (Person) obj;

        Pattern pattern = Pattern.compile("[a-zA-Z]+[.][a-zA-Z]+@\\btieto\\b[.]\\bcom\\b",
                Pattern.CASE_INSENSITIVE);

        if (!(pattern.matcher(person.getEmail()).matches())) {
            errors.rejectValue("email", "email.invalid", "Invalid email! Please enter Tieto email.");
        }

        if (personService.checkAccountAlreadyExist(person.getEmail())) {
            errors.rejectValue("email", "email.exists", "The username already exists. Please use a different username");
        }




        System.out.println(errors);
    }
}
