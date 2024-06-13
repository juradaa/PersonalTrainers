package edu.jurada.backend.models.trips.validation;

import edu.jurada.backend.models.trips.TrainingTrip;
import edu.jurada.backend.models.trips.TripStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class TripStatusValidator implements ConstraintValidator<ValidTripStatus, TrainingTrip> {

	@Override
	public boolean isValid(TrainingTrip trainingTrip, ConstraintValidatorContext constraintValidatorContext) {
		List<TripStatus> validManuallySetStatuses = List.of(TripStatus.PUBLISHED, TripStatus.DRAFT, TripStatus.CANCELLED, TripStatus.INTERRUPTED);
		return validManuallySetStatuses.contains(trainingTrip.getBaseStatus());
	}
}
