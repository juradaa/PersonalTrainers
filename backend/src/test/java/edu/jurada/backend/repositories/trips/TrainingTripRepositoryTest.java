package edu.jurada.backend.repositories.trips;

import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.people.TrainerType;
import edu.jurada.backend.models.trips.TrainingTrip;
import edu.jurada.backend.models.trips.TripStatus;
import edu.jurada.backend.repositories.people.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TrainingTripRepositoryTest {
	@Autowired
	private TrainingTripRepository underTest;

	@Autowired
	private TrainerRepository trainerRepository;

	@Test
	void testThatExistsOverlappingReturnsTrueWhenOverlapping(){
		// given
		Trainer trainer = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Hello there")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Joe soe")
				.alias("joesoe")
				.build();
		trainerRepository.save(trainer);
		TrainingTrip trip = TrainingTrip.builder()
				.trainer(trainer)
				.name("Calisthenics")
				.destination("Egypt")
				.description("Body-weight exercises for strength and hypertrophy")
				.topics(Set.of("Strength training", "Hypertrophic training"))
				.startDate(LocalDate.parse("2024-06-15"))
				.endDate(LocalDate.parse("2024-06-27"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();
		underTest.save(trip);

		// when
		boolean exists = underTest.existsOverlapping(trainer, LocalDate.parse("2024-06-23"), LocalDate.parse("2024-07-03"));

		// then
		assertTrue(exists);
	}

	@Test
	void testThatExistsOverlappingReturnsFalseWhenNotOverlapping(){
		// given
		Trainer trainer = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Hello there")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Joe soe")
				.alias("joesoe")
				.build();
		trainerRepository.save(trainer);
		TrainingTrip trip = TrainingTrip.builder()
				.trainer(trainer)
				.name("Calisthenics")
				.destination("Egypt")
				.description("Body-weight exercises for strength and hypertrophy")
				.topics(Set.of("Strength training", "Hypertrophic training"))
				.startDate(LocalDate.parse("2024-06-15"))
				.endDate(LocalDate.parse("2024-06-27"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();
		underTest.save(trip);

		// when
		boolean exists = underTest.existsOverlapping(trainer, LocalDate.parse("2024-06-03"), LocalDate.parse("2024-06-09"));

		// then
		assertFalse(exists);
	}

	@Test
	void testThatExistsOverlappingReturnsTrueWhenContaining(){
		// given
		Trainer trainer = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Hello there")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Joe soe")
				.alias("joesoe")
				.build();
		trainerRepository.save(trainer);
		TrainingTrip trip = TrainingTrip.builder()
				.trainer(trainer)
				.name("Calisthenics")
				.destination("Egypt")
				.description("Body-weight exercises for strength and hypertrophy")
				.topics(Set.of("Strength training", "Hypertrophic training"))
				.startDate(LocalDate.parse("2024-06-15"))
				.endDate(LocalDate.parse("2024-06-27"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();
		underTest.save(trip);

		// when
		boolean exists = underTest.existsOverlapping(trainer, LocalDate.parse("2024-06-12"), LocalDate.parse("2024-07-03"));

		// then
		assertTrue(exists);
	}

	@Test
	void testThatFindTrainersTripsAfterFindsTrainersTrips(){
		//given
		Trainer trainer = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Hello there")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Joe soe")
				.alias("joesoe")
				.build();
		trainerRepository.save(trainer);

		TrainingTrip tt1 = TrainingTrip.builder()
				.trainer(trainer)
				.name("Calisthenics")
				.destination("Egypt")
				.description("Body-weight exercises for strength and hypertrophy")
				.topics(Set.of("Strength training", "Hypertrophic training"))
				.startDate(LocalDate.parse("2024-06-15"))
				.endDate(LocalDate.parse("2024-06-20"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();


		TrainingTrip tt2 = TrainingTrip.builder()
				.name("Cardio")
				.trainer(trainer)
				.destination("London")
				.description("Training for weight-loss and cardiovascular health")
				.topics(Set.of("weight-loss", "cardiovascular health"))
				.startDate(LocalDate.parse("2024-07-15"))
				.endDate(LocalDate.parse("2024-07-24"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();

		TrainingTrip tt3 = TrainingTrip.builder()
				.name("Training in the gym")
				.trainer(trainer)
				.destination("Warsaw")
				.description("Utilising free weights and machines in training")
				.topics(Set.of("Muscle anatomy", "Latest research on lengthened partials"))
				.startDate(LocalDate.parse("2024-06-25"))
				.endDate(LocalDate.parse("2024-07-07"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();
		underTest.saveAll(List.of(tt1,tt2,tt3));

		// when
		List<TrainingTrip> trips = underTest.findTrainersTripsAfter(trainer.getId(), LocalDate.parse("2024-06-21"));

		// then
		assertEquals(2, trips.size());
		assertTrue(trips.contains(tt2));
		assertTrue(trips.contains(tt3));
		assertFalse(trips.contains(tt1));

	}
}