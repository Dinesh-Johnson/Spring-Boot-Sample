package com.xworkz.library_mangement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class BookCodeValidator implements ConstraintValidator<ValidBookCode, String> {
    
    private static final Pattern BOOK_CODE_PATTERN = Pattern.compile("^[A-Z]{4}\\d{6}$");
    
    @Override
    public void initialize(ValidBookCode constraintAnnotation) {

    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return BOOK_CODE_PATTERN.matcher(value).matches();
    }
}
