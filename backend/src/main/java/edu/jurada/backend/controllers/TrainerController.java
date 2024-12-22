package edu.jurada.backend.controllers;

import edu.jurada.backend.models.dto.people.SimpleTrainerViewDto;
import edu.jurada.backend.models.dto.trips.SimpleTripDateViewDto;
import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.trips.TrainingTrip;
import edu.jurada.backend.services.TrainerService;
import edu.jurada.backend.services.TrainingTripService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/trainers")
@RequiredArgsConstructor
public class TrainerController {

	private final TrainerService trainerService;
	private final ModelMapper modelMapper;
	private final TrainingTripService trainingTripService;

	@GetMapping("/seniors")
	public ResponseEntity<List<SimpleTrainerViewDto>> search(@RequestParam String searched) {
		List<Trainer> trainers = trainerService.searchSeniorTrainers(searched);
		List<SimpleTrainerViewDto> trainerDtos = trainers.stream().map((element) -> modelMapper.map(element, SimpleTrainerViewDto.class)).toList();
		return new ResponseEntity<>(trainerDtos, HttpStatus.OK);
	}

	@GetMapping("{id}/future/trips")
	public ResponseEntity<List<SimpleTripDateViewDto>> getTrainersFutureTrips(@PathVariable("id") long id) {
		if (!trainerService.exists(id)) {
			return ResponseEntity.notFound().build();
		}
		List<TrainingTrip> futureTripsForTrainer = trainingTripService.getFutureTripsForTrainer(id);
		List<SimpleTripDateViewDto> dtos = futureTripsForTrainer.stream()
				.map((element) -> modelMapper.map(element, SimpleTripDateViewDto.class))
				.toList();
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping("seniors/count")
	public ResponseEntity<Long> getSeniorTrainerCount(){
		Long count = trainerService.countSeniors();
		return new ResponseEntity<>(count, HttpStatus.OK);
	}


}
