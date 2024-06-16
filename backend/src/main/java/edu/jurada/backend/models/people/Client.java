package edu.jurada.backend.models.people;


import edu.jurada.backend.models.entitlements.ClientsSubscription;
import edu.jurada.backend.models.trips.TrainingTrip;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Person {


	@CreditCardNumber(ignoreNonDigitCharacters = true) // allows for e.g. dashes
	@NotNull
	private String creditCardNumber;

	@Positive
	private double weight;
	@Positive
	private double height;


	private String goal;


	@OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	private Set<ClientsSubscription> clientsSubscriptions = new HashSet<>();

	@ManyToMany(mappedBy = "clients")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	private Set<TrainingTrip> trainingTrips = new HashSet<>();


	/**
	 * @inheritDocs
	 * Client classes implementation checks whether a client has a subscription
	 * that is currently active
	 */
	@Override
	public boolean checkIfShouldNotify() {
		LocalDate now = LocalDate.now();
		boolean existsCurrentSubscription = this.clientsSubscriptions.stream()
				.anyMatch(sub -> !now.isBefore(sub.getStartDate()) && now.isAfter(sub.getEndDate()));
		return existsCurrentSubscription;
	}

	public double getBmi(){
		return weight/Math.pow(height,2);
	}
}
