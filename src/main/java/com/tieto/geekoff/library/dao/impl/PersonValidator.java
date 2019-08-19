package com.tieto.geekoff.library.dao.impl;

import com.tieto.geekoff.library.frontend.models.Book;
import com.tieto.geekoff.library.frontend.models.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass) || Book.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "email", "required.email","Email is required.");

        System.out.println("VALIDATE");
        /*
        Person person = (Person) obj;
        if (person.getFirstName() == null || person.getFirstName().trim().length() == 0){
            errors.rejectValue("firstName", "firstName.required", "Name field is missing");
        }

         */

        System.out.println(errors);
    }
}
