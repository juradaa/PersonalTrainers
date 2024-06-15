package edu.jurada.backend.services;


import edu.jurada.backend.models.trips.TrainingTrip;

import java.util.List;

public interface TrainingTripService {
	List<TrainingTrip> getFutureTripsForTrainer(long trainerId);
}
