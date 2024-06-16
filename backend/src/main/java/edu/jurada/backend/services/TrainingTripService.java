package edu.jurada.backend.services;


import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.trips.Gear;
import edu.jurada.backend.models.trips.TrainingTrip;

import java.util.List;

public interface TrainingTripService {
	/**
	 * Finds trips for trainer that will end in the future. I.e. trips that have not
	 * yet ended or even begun.
	 * @param trainerId id of the trainer whose trips will be searched
	 * @return list of training trips that end in the future
	 */
	List<TrainingTrip> getFutureTripsForTrainer(long trainerId);

	/**
	 * Creates a training trip and all necessary associations
	 * @param trainingTrip training trip with all basic data
	 * @param gear list of gears used by that training trip
	 * @param trainer trainer responsible for that trip
	 * @return saved Training trip
	 * @throws edu.jurada.backend.exceptions.TripsOverlapException when the trip overlaps
	 * with an existing trip
	 */
	TrainingTrip createTrip(TrainingTrip trainingTrip, List<Gear> gear, Trainer trainer);
}
