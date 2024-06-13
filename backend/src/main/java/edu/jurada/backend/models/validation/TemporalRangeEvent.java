package edu.jurada.backend.models.validation;

import java.time.LocalDate;

public interface TemporalRangeEvent {
	LocalDate getStartDate();
	LocalDate getEndDate();
}
