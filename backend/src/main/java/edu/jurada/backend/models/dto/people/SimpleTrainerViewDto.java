package edu.jurada.backend.models.dto.people;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimpleTrainerViewDto {
	private Long id;
	private String name;
	private String bio;
	private String alias;

}
