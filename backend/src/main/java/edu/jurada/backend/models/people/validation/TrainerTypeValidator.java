package edu.jurada.backend.models.people.validation;

import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.people.TrainerType;
import edu.jurada.backend.utils.ValidationUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TrainerTypeValidator implements ConstraintValidator<ValidTrainerType, Trainer> {
	@Override
	public void initialize(ValidTrainerType constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Trainer trainer, ConstraintValidatorContext constraintValidatorContext) {


		// validating assistants fields
		boolean isAssistant = trainer.getType().equals(TrainerType.ASSISTANT);
		boolean areAssistantFieldsValid = ValidationUtil.isNullValidForType(isAssistant, trainer.getHireDate());
		if(!areAssistantFieldsValid){
			return false;
		}

		// validating seniors fields
		boolean isSenior = trainer.getType().equals(TrainerType.SENIOR);
		boolean areSeniorFieldsValid = ValidationUtil.isStringExistenceValidForType(isSenior, trainer.getSpecialization());
		if(!areSeniorFieldsValid){
			return false;
		}


		return true;
	}
}
