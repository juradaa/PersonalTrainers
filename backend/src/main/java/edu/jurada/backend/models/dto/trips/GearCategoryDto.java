package edu.jurada.backend.models.dto.trips;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GearCategoryDto {
	private Long id;
	private String description;
	private String name;
}
