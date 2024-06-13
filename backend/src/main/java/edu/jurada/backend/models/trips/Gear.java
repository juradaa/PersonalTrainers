package edu.jurada.backend.models.trips;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Gear {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String name;

	private String exerciseDescription;

	@ManyToOne(optional = false)
	@NotNull
	@JoinColumn(name = "gear_category_id", nullable = false, updatable = false)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private GearCategory gearCategory;



	@ManyToMany(mappedBy = "gearSet")
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<TrainingTrip> trainingTrips = new HashSet<>();




}
