package edu.jurada.backend.models.dto.trips;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimpleTripDateViewDto {
	private LocalDate startDate;
	private LocalDate endDate;
	private String name;
}
