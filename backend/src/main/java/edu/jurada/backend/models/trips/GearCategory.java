package edu.jurada.backend.models.trips;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GearCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String description;

	@OneToMany(mappedBy = "gearCategory", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Gear> gearSet = new HashSet<>();
}
