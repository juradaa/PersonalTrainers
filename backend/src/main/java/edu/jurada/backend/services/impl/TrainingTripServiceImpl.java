package edu.jurada.backend.services.impl;

import edu.jurada.backend.models.trips.TrainingTrip;
import edu.jurada.backend.repositories.trips.TrainingTripRepository;
import edu.jurada.backend.services.TrainingTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingTripServiceImpl implements TrainingTripService {
	private final TrainingTripRepository trainingTripRepository;

	@Override
	public List<TrainingTrip> getFutureTripsForTrainer(long trainerId) {
		return trainingTripRepository.findTrainersFutureTrips(trainerId);
	}
}
