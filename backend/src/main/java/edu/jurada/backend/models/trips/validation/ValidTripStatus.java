package edu.jurada.backend.models.trips.validation;

import edu.jurada.backend.models.entitlements.validation.GymHoursValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TripStatusValidator.class)
public @interface ValidTripStatus {
	String message() default "Only statuses: draft, published, interrupted and cancelled can be set manually";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
