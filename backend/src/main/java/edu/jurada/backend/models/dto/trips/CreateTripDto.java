package edu.jurada.backend.models.dto.trips;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.jurada.backend.models.validation.TemporalRangeEvent;
import edu.jurada.backend.models.validation.ValidTemporalRange;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ValidTemporalRange
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CreateTripDto implements TemporalRangeEvent {
	// only for response
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	@Size(min = 25)
	private String description;
	@NotBlank
	private String destination;
	@NotEmpty
	@NotNull
	private List<TripTopicWrapperDto> topics;
	@NotNull
	private LocalDate startDate;
	@NotNull
	private LocalDate endDate;

	private boolean shouldPublishImmediately;
	@NotNull
	private List<Long> gearIds;
	private long trainerId;
}
