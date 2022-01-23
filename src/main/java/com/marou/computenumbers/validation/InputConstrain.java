package com.marou.computenumbers.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
//@Constraint(validatedBy = InputValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface InputConstrain {
  String message() default "Invalid input";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
