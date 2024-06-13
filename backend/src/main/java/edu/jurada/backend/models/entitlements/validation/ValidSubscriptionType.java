package edu.jurada.backend.models.entitlements.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=SubscriptionTypeValidator.class)
public @interface ValidSubscriptionType {
	String message() default "Subscription state does not match its type";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
