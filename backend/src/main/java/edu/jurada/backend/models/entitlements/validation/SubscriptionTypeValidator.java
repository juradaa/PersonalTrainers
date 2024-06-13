package edu.jurada.backend.models.entitlements.validation;

import edu.jurada.backend.models.entitlements.SubcriptionType;
import edu.jurada.backend.models.entitlements.Subscription;
import edu.jurada.backend.utils.ValidationUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SubscriptionTypeValidator implements ConstraintValidator<ValidSubscriptionType, Subscription> {
	@Override
	public boolean isValid(Subscription subscription, ConstraintValidatorContext constraintValidatorContext) {
		boolean isDiet = subscription.getSubscriptionTypes().contains(SubcriptionType.DIET);
		boolean areDietFieldsValid = ValidationUtil.isNullValidForType(isDiet, subscription.getDailyCalorieCount()) &&
				ValidationUtil.isStringExistenceValidForType(isDiet, subscription.getCuisineDescription());
		if(!areDietFieldsValid){
			return false;
		}
		// validation requires loading dietary restrictions for entities that are not a diet
		if(!isDiet && subscription.getDietaryRestrictions().size() != 0){
			return false;
		}

		boolean isGym = subscription.getSubscriptionTypes().contains(SubcriptionType.GYM);
		boolean areGymFieldsValid = ValidationUtil.isNullValidForType(isGym, subscription.getEntrancesPerWeek(), subscription.getHoursWithTrainer());
		if(!areGymFieldsValid){
			return false;
		}
		return true;
	}
}
