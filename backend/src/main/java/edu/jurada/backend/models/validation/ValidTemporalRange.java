package edu.jurada.backend.models.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=TemporalRangeValidator.class)
public @interface ValidTemporalRange {

	String message() default "End date is before the start Date";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
