package edu.jurada.backend.services.impl;

import edu.jurada.backend.exceptions.TripsOverlapException;
import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.trips.Gear;
import edu.jurada.backend.models.trips.TrainingTrip;
import edu.jurada.backend.repositories.trips.GearRepository;
import edu.jurada.backend.repositories.trips.TrainingTripRepository;
import edu.jurada.backend.services.TrainingTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingTripServiceImpl implements TrainingTripService {
	private final TrainingTripRepository trainingTripRepository;
	private final GearRepository gearRepository;

	@Override
	public List<TrainingTrip> getFutureTripsForTrainer(long trainerId) {
		return trainingTripRepository.findTrainersFutureTrips(trainerId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TrainingTrip createTrip(TrainingTrip trainingTrip, List<Gear> gear, Trainer trainer) {
		if (trainingTripRepository.existsOverlapping(trainingTrip.getStartDate(), trainingTrip.getEndDate())) {
			throw new TripsOverlapException();
		}
		for (Gear g : gear) {
			g.getTrainingTrips().add(trainingTrip);
			trainingTrip.getGearSet().add(g);
		}
		trainingTrip.setTrainer(trainer);
		trainer.getTrainingTrips().add(trainingTrip);
		TrainingTrip tripFromDb = trainingTripRepository.save(trainingTrip);
		gearRepository.saveAll(gear); // not necessary, because @transactional = dirty check
		return tripFromDb;
	}
}
