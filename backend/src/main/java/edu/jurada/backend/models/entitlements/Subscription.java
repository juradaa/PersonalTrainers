package edu.jurada.backend.models.entitlements;

import edu.jurada.backend.models.entitlements.validation.ValidSubscriptionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ValidSubscriptionType
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	@NotNull
	@Positive
	private BigDecimal cost;

	@Builder.Default
	@ElementCollection
	@CollectionTable(name = "subscription_type", joinColumns = @JoinColumn(name = "subscription_id"))
	@Enumerated(EnumType.STRING)
	@NotEmpty
	private Set<SubcriptionType> subscriptionTypes = new HashSet<>();

	@Min(1)
	@Max(7)
	private Integer entrancesPerWeek;

	private Integer hoursWithTrainer;


	@Builder.Default
	@ElementCollection
	@CollectionTable(name = "dietary_restriction", joinColumns = @JoinColumn(name = "subscription_id"))
	private Set<String> dietaryRestrictions = new HashSet<>();

	@Positive
	private Integer dailyCalorieCount;


	private String cuisineDescription;


	@OneToMany(mappedBy = "subscription", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	private Set<ClientsSubscription> clientsSubscriptions = new HashSet<>();


}
