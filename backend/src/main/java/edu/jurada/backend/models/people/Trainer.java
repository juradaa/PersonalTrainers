package edu.jurada.backend.models.people;

import edu.jurada.backend.exceptions.WrongTypeException;
import edu.jurada.backend.models.entitlements.Certification;
import edu.jurada.backend.models.entitlements.comparators.ByIssueDateComparator;
import edu.jurada.backend.models.people.validation.ValidTrainerType;
import edu.jurada.backend.models.trips.TrainingTrip;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SortComparator;
import org.hibernate.annotations.SortNatural;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@SuperBuilder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ValidTrainerType
public class Trainer extends Person {

	@ToString.Exclude
	private final int ALERT_MONTH_LIMIT= 3;

	@NotNull
	@DecimalMin(value = "0", inclusive = false)
	private BigDecimal salary;

	@NotBlank
	private String bio;


	// association with attribute
	@OneToMany(mappedBy = "trainer", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Builder.Default
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@SortComparator(ByIssueDateComparator.class)
	private SortedSet<Certification> certifications = new TreeSet<>(new ByIssueDateComparator());

	@Enumerated(EnumType.STRING)
	@NotNull
	private TrainerType type;


	@PastOrPresent
	private LocalDate hireDate;


	private String specialization;

	@OneToMany(mappedBy = "trainer", orphanRemoval = true)
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<TrainingTrip> trainingTrips = new HashSet<>();


	public void promote(String specialization) {
		this.specialization = specialization;
		this.hireDate = null;
		this.type = TrainerType.SENIOR;
	}

	public void demote(LocalDate hireDate) {
		this.specialization = null;
		this.hireDate = hireDate;
		this.type = TrainerType.ASSISTANT;
	}

	/**
	 * @inheritDocs
	 * Trainers implementation checks: for seniors whether they are currently
	 * responsible for a trip and for assistants whether they have more than
	 * 3 months of experience
	 *
	 */
	@Override
	public boolean checkIfShouldNotify() {
		if (this.type == TrainerType.SENIOR) {
			LocalDate now = LocalDate.now();
			boolean isNotBusy = this.trainingTrips.stream().allMatch(tt -> now.isBefore(tt.getStartDate()) || now.isAfter(tt.getEndDate()));
			return isNotBusy;
		}
		if(this.type == TrainerType.ASSISTANT){
			return ChronoUnit.MONTHS.between(this.hireDate, LocalDate.now()) >= 3;

		}
		throw new WrongTypeException();
	}
}
