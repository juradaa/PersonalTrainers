package edu.jurada.backend.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.jurada.backend.models.dto.people.SimpleTrainerViewDto;
import edu.jurada.backend.models.dto.trips.SimpleTripDateViewDto;
import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.people.TrainerType;
import edu.jurada.backend.models.trips.TrainingTrip;
import edu.jurada.backend.models.trips.TripStatus;
import edu.jurada.backend.repositories.people.TrainerRepository;
import edu.jurada.backend.repositories.trips.TrainingTripRepository;
import edu.jurada.backend.services.TrainerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TrainerControllerIT {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	TrainerRepository trainerRepository;
	@Autowired
	TrainingTripRepository trainingTripRepository;

	@MockBean
	private Clock clock;

	@Test
	void testThatSearchingSeniorTrainersFindsAndSortsSeniorTrainers() throws Exception {

		// given
		Trainer ts1 = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Strength training")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("joe")
				.alias("john")
				.build();

		Trainer ts2 = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Hello there")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Joe Soe")
				.alias("soe")
				.build();

		Trainer ts3 = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Strength training")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Marry Joey")
				.alias("joey")
				.build();
		trainerRepository.saveAll(List.of(ts1, ts2, ts3));

		// when
		MvcResult mvcResult = mockMvc.perform(get("/api/trainers/seniors?searched=joe")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		// then
		List<SimpleTrainerViewDto> simpleTrainerViewDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<SimpleTrainerViewDto>>() {
		});
		List<Long> actual = simpleTrainerViewDtos.stream().map(SimpleTrainerViewDto::getId).toList();
		List<Long> expected = List.of(ts1.getId(), ts3.getId(), ts2.getId());
		assertEquals(expected, actual);
	}

	@Test
	void testThatGetttingSeniorsFutureTripsGetsAllSeniorsFutureTrips() throws Exception {

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
		trainingTripRepository.saveAll(List.of(tt1, tt2, tt3));

		given(clock.instant()).willReturn(Instant.parse("2024-07-01T12:15:00Z"));
		given(clock.getZone()).willReturn(ZoneId.of("UTC"));

		//when
		MvcResult mvcResult = mockMvc.perform(get("/api/trainers/" + trainer.getId() + "/future/trips")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		List<SimpleTripDateViewDto> simpleTripDateViewDtos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<SimpleTripDateViewDto>>() {
		});
		//then
		Set<String> expected = Set.of(tt2.getName(), tt3.getName());
		Set<String> actual = simpleTripDateViewDtos.stream().map(SimpleTripDateViewDto::getName).collect(Collectors.toSet());
		assertEquals(expected, actual);
	}
}
