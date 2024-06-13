package edu.jurada.backend.models.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TemporalRangeValidator implements ConstraintValidator<ValidTemporalRange, TemporalRangeEvent> {


	@Override
	public boolean isValid(TemporalRangeEvent temporalRangeEvent, ConstraintValidatorContext constraintValidatorContext) {
		boolean areValidatable = temporalRangeEvent.getStartDate() != null && temporalRangeEvent.getEndDate() != null;
		if (!areValidatable) {
			return true;
		}

		boolean isStartAfterEnd = temporalRangeEvent.getStartDate().isAfter(temporalRangeEvent.getEndDate());
		if (isStartAfterEnd) {
			return false;
		}

		return true;
	}
}
