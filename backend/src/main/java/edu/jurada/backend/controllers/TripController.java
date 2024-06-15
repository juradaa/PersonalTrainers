package edu.jurada.backend.controllers;

import edu.jurada.backend.exceptions.TripsOverlapException;
import edu.jurada.backend.models.dto.trips.CreateTripDto;
import edu.jurada.backend.models.dto.trips.TripTopicWrapperDto;
import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.trips.Gear;
import edu.jurada.backend.models.trips.TrainingTrip;
import edu.jurada.backend.models.trips.TripStatus;
import edu.jurada.backend.services.GearService;
import edu.jurada.backend.services.TrainerService;
import edu.jurada.backend.services.TrainingTripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trainingTrips")
@RequiredArgsConstructor
public class TripController {
	private final TrainingTripService trainingTripService;
	private final TrainerService trainerService;
	private final GearService gearService;

	Logger logger = LoggerFactory.getLogger(TripController.class);

	@PostMapping
	@Transactional
	public ResponseEntity<CreateTripDto> postTrip(@RequestBody @Valid CreateTripDto tripData) {
		List<Long> gearIds = tripData.getGearIds().stream().distinct().toList();
		List<Gear> gearFromDb = gearService.findAllById(gearIds);
		logger.trace(gearFromDb.toString());
		if (gearFromDb.size() != gearIds.size()) {
			return ResponseEntity.notFound().build();
		}
		Optional<Trainer> optionalTrainer = trainerService.findById(tripData.getTrainerId());
		if (optionalTrainer.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Trainer trainer = optionalTrainer.get();
		logger.trace(trainer.toString());

		TrainingTrip trainingTrip = TrainingTrip.builder()
				.name(tripData.getName())
				.description(tripData.getDescription())
				.destination(tripData.getDestination())
				.topics(tripData.getTopics().stream().map(TripTopicWrapperDto::getTopic).collect(Collectors.toSet()))
				.startDate(tripData.getStartDate())
				.endDate(tripData.getEndDate())
				.baseStatus(tripData.isShouldPublishImmediately() ? TripStatus.PUBLISHED : TripStatus.DRAFT)
				.build();

		logger.trace("here");
		TrainingTrip trip = trainingTripService.createTrip(trainingTrip, gearFromDb, trainer);
		logger.trace("there");
		tripData.setId(trip.getId());
		return new ResponseEntity<>(tripData, HttpStatus.OK);
	}
}
