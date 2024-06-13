package edu.jurada.backend.models.entitlements.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GymHoursValidator.class)
public @interface ValidGymHours {
	String message() default "There are more hours with trainer than entrances in a week";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
