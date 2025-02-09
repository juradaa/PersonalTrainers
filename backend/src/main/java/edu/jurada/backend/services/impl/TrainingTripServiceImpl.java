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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingTripServiceImpl implements TrainingTripService {
	private final TrainingTripRepository trainingTripRepository;
	private final GearRepository gearRepository;
	private final Clock clock;

	@Override
	public List<TrainingTrip> getFutureTripsForTrainer(long trainerId) {
		return trainingTripRepository.findTrainersTripsAfter(trainerId, LocalDate.now(clock));
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public TrainingTrip createTrip(TrainingTrip trainingTrip, List<Gear> gear, Trainer trainer) {
		// propagation is the same as default
		// isolation is serializable, because we do not want an overlapping trip phantom to appear
		if (trainingTripRepository.existsOverlapping(trainer ,trainingTrip.getStartDate(), trainingTrip.getEndDate())) {
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
