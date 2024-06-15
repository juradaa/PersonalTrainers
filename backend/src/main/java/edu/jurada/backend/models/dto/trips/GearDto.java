package edu.jurada.backend.models.dto.trips;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GearDto {
	private Long id;
	private String name;
	private String exerciseDescription;

}
