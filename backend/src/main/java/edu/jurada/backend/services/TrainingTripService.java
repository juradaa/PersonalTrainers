package edu.jurada.backend.services;


import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.trips.Gear;
import edu.jurada.backend.models.trips.TrainingTrip;

import java.util.List;

public interface TrainingTripService {
	List<TrainingTrip> getFutureTripsForTrainer(long trainerId);
	TrainingTrip createTrip(TrainingTrip trainingTrip, List<Gear> gear, Trainer trainer);
}
