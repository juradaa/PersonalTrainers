package edu.jurada.backend.models.people.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TrainerTypeValidator.class)
public @interface  ValidTrainerType {

	String message() default "Trainer state does not match its type";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
