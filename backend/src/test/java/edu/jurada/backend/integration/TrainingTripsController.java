package edu.jurada.backend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.jurada.backend.models.dto.trips.CreateTripDto;
import edu.jurada.backend.models.dto.trips.TripTopicWrapperDto;
import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.people.TrainerType;
import edu.jurada.backend.models.trips.TrainingTrip;
import edu.jurada.backend.repositories.people.TrainerRepository;
import edu.jurada.backend.repositories.trips.TrainingTripRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TrainingTripsController {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	TrainerRepository trainerRepository;

	@Autowired
	TrainingTripRepository trainingTripRepository;

	@Test
	void testThatCreateTripSavesNewTrip() throws Exception {
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
		CreateTripDto tripDto = CreateTripDto.builder()
				.name("Calisthenics")
				.destination("Egypt")
				.description("Body-weight exercises for strength and hypertrophy")
				.topics(List.of(new TripTopicWrapperDto("Some topic")))
				.startDate(LocalDate.parse("2024-06-15"))
				.endDate(LocalDate.parse("2024-06-20"))
				.shouldPublishImmediately(true)
				.gearIds(List.of())
				.trainerId(trainer.getId())
				.build();
		// when
		mockMvc.perform(post("/api/trainingTrips")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(tripDto)))
				.andExpect(status().isCreated());

		// then
		List<TrainingTrip> all = trainingTripRepository.findAll();
		assertEquals(1, all.size());
		String actualName = all.get(0).getName();
		assertEquals(tripDto.getName(), actualName);


	}
}
