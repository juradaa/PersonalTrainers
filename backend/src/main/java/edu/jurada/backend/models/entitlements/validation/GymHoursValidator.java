package edu.jurada.backend.models.entitlements.validation;

import edu.jurada.backend.models.entitlements.Subscription;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GymHoursValidator implements ConstraintValidator<ValidGymHours, Subscription> {

	@Override
	public boolean isValid(Subscription subscription, ConstraintValidatorContext constraintValidatorContext) {
		boolean areValidatable = subscription.getHoursWithTrainer()!= null && subscription.getEntrancesPerWeek() != null;
		if(!areValidatable){
			return true;
		}

		if(subscription.getHoursWithTrainer() > subscription.getEntrancesPerWeek()){
			return false;
		}
		return true;
	}
}
