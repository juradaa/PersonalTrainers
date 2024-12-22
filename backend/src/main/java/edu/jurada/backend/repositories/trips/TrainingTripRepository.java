package edu.jurada.backend.repositories.trips;

import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.trips.TrainingTrip;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;

public interface TrainingTripRepository extends JpaRepository<TrainingTrip, Long> {
	@Query("from TrainingTrip t where t.trainer.id =:trainerId and t.endDate >= :date")
	List<TrainingTrip> findTrainersTripsAfter(long trainerId, LocalDate date);

	@Query("select count(*) > 0 from TrainingTrip  where trainer = :trainer and  endDate >= :start and :end >= startDate")
	boolean existsOverlapping(Trainer trainer, LocalDate start, LocalDate end);

}
