package edu.jurada.backend.services.impl;

import edu.jurada.backend.exceptions.TripsOverlapException;
import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.people.TrainerType;
import edu.jurada.backend.models.trips.Gear;
import edu.jurada.backend.models.trips.GearCategory;
import edu.jurada.backend.models.trips.TrainingTrip;
import edu.jurada.backend.models.trips.TripStatus;
import edu.jurada.backend.repositories.trips.GearRepository;
import edu.jurada.backend.repositories.trips.TrainingTripRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class TrainingTripServiceImplTest {
	@InjectMocks
	private TrainingTripServiceImpl underTest;
	@Mock
	private Clock clock;
	@Mock
	private TrainingTripRepository trainingTripRepository;
	@Mock
	private GearRepository gearRepository;

	@Test
	void testThatGetFutureTripsForTrainerGetsFutureTrips() {

		// given
		given(clock.instant()).willReturn(Instant.parse( "2024-01-01T12:15:00Z"));
		given(clock.getZone()).willReturn(ZoneId.of("UTC"));
		given(trainingTripRepository.findTrainersTripsAfter(anyLong(), any())).willReturn(List.of(TrainingTrip.builder().id(12L).build()));

		// when
		underTest.getFutureTripsForTrainer(22L);
		// then
		ArgumentCaptor<Long> trainerIdCaptor = ArgumentCaptor.forClass(Long.class);
		ArgumentCaptor<LocalDate> localDateCaptor = ArgumentCaptor.forClass(LocalDate.class);
		verify(trainingTripRepository).findTrainersTripsAfter(trainerIdCaptor.capture(), localDateCaptor.capture());
		Long trainerId = trainerIdCaptor.getValue();
		assertEquals(22L, trainerId);
		LocalDate localDate = localDateCaptor.getValue();
		assertEquals(LocalDate.parse("2024-01-01"),localDate);

	}

	@Test
	void testThatCreateTripCreatesNewTrip() {

		// given
		Trainer trainer = Trainer.builder()
				.id(1L)
				.type(TrainerType.SENIOR)
				.specialization("Strength training")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Mary sue")
				.alias("marsue")
				.build();
		TrainingTrip trainingTrip = TrainingTrip.builder()
				.id(1L)
				.trainer(trainer)
				.name("Calisthenics")
				.destination("Egypt")
				.description("Body-weight exercises for strength and hypertrophy")
				.topics(Set.of("Strength training", "Hypertrophic training"))
				.startDate(LocalDate.parse("2024-06-15"))
				.endDate(LocalDate.parse("2024-06-27"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();

		GearCategory gc1 = GearCategory.builder()
				.id(1L)
				.name("Cardio")
				.description("Exercises for strengthening the cardiovascular system and burning calories.")
				.build();
		Gear g1 = Gear.builder()
				.id(1L)
				.gearCategory(gc1)
				.name("Bicycle")
				.exerciseDescription("Cycling is a low impact cardio modality that does not interfere with strength training")
				.build();

		Gear g2 = Gear.builder()
				.id(2L)
				.gearCategory(gc1)
				.name("Treadmill")
				.exerciseDescription("Running on a treadmill is a great way to burn calories, however it is also hard on the joints")
				.build();

		given(trainingTripRepository.existsOverlapping(any(Trainer.class),any(LocalDate.class),any(LocalDate.class))).willReturn(false);
		given(trainingTripRepository.save(trainingTrip)).willReturn(trainingTrip);
		given(gearRepository.saveAll(List.of(g1,g2))).willReturn(List.of(g1, g2));

		//when
		underTest.createTrip(trainingTrip, List.of(g1,g2), trainer);

		//then
		verify(trainingTripRepository).existsOverlapping(trainer,LocalDate.parse("2024-06-15"),LocalDate.parse("2024-06-27"));
		verify(trainingTripRepository).save(trainingTrip);
		verify(gearRepository).saveAll(List.of(g1,g2));


	}
	@Test
	void testThatCreateTripThrowsWhenOverlapping() {
		// given
		Trainer trainer = Trainer.builder()
				.id(1L)
				.type(TrainerType.SENIOR)
				.specialization("Strength training")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Mary sue")
				.alias("marsue")
				.build();
		TrainingTrip trainingTrip = TrainingTrip.builder()
				.id(1L)
				.trainer(trainer)
				.name("Calisthenics")
				.destination("Egypt")
				.description("Body-weight exercises for strength and hypertrophy")
				.topics(Set.of("Strength training", "Hypertrophic training"))
				.startDate(LocalDate.parse("2024-06-15"))
				.endDate(LocalDate.parse("2024-06-27"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();

		given(trainingTripRepository.existsOverlapping(any(Trainer.class),any(LocalDate.class),any(LocalDate.class))).willReturn(true);
		assertThrows(TripsOverlapException.class, ()->{
			underTest.createTrip(trainingTrip,List.of(), trainer);
		});
	}
}
