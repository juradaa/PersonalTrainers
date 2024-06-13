package edu.jurada.backend.models.entitlements;

import edu.jurada.backend.models.people.Client;
import edu.jurada.backend.models.validation.TemporalRangeEvent;
import edu.jurada.backend.models.validation.ValidTemporalRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ValidTemporalRange
// no unique constraint because of <<history>> stereotype
public class ClientsSubscription implements TemporalRangeEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private LocalDate startDate;
	@NotNull
	private LocalDate endDate;
	private boolean isPaid;


	@ManyToOne
	@JoinColumn(name = "subscription_id", nullable = false)
	@NotNull
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Subscription subscription;
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	@NotNull
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Client client;
}
