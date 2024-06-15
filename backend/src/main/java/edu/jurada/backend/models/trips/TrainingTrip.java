package edu.jurada.backend.models.trips;

import edu.jurada.backend.models.people.Client;
import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.trips.validation.ValidTripStatus;
import edu.jurada.backend.models.validation.TemporalRangeEvent;
import edu.jurada.backend.models.validation.ValidTemporalRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ValidTemporalRange
@ValidTripStatus
@Table(indexes = @Index(name = "end_date_idx", columnList = "end_date"))
public class TrainingTrip implements TemporalRangeEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	@Size(min = 25)
	private String description;

	@NotBlank
	private String destination;

	@ElementCollection
	@NotEmpty
	@CollectionTable
	private Set<String> topics;

	@NotNull
	@Column(name = "start_date")
	private LocalDate startDate;
	@NotNull
	@Column(name = "end_date")
	private LocalDate endDate;

	@Enumerated(EnumType.STRING)
	@NotNull
	private TripStatus baseStatus;


	@ManyToMany
	@JoinTable(
			name = "training_trip_gear",
			joinColumns = {@JoinColumn(name = "training_trip_id")},
			inverseJoinColumns = {@JoinColumn(name = "gear_id")}
	)
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Gear> gearSet = new HashSet<>();



	@ManyToMany
	@JoinTable(
			name="training_trip_client",
			joinColumns = {@JoinColumn(name = "training_trip_id")},
			inverseJoinColumns = {@JoinColumn(name = "client_id")}
	)
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Client> clients = new HashSet<>();


	//TODO: add trainer, clients and validators
	@ManyToOne
	@JoinColumn(name = "trainer_id", nullable = false)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Trainer trainer;






	private TripStatus getStatus(){
		if(baseStatus!= TripStatus.PUBLISHED){
			return baseStatus;
		}
		LocalDate now = LocalDate.now();
		if(now.isAfter(endDate)){
			return TripStatus.FINISHED;
		}
		if(!now.isBefore(startDate)){
			return TripStatus.IN_PROGRESS;
		}
		return TripStatus.PUBLISHED;
	}
}
