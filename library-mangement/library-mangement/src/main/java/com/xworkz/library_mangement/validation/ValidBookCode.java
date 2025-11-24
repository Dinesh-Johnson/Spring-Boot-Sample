package com.xworkz.library_mangement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BookCodeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBookCode {
    String message() default "Invalid book code. Must be 4 uppercase letters followed by 6 digits (e.g., ABCD123456)";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
