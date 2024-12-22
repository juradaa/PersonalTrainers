package edu.jurada.backend.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.jurada.backend.models.dto.trips.GearCategoryDto;
import edu.jurada.backend.models.trips.Gear;
import edu.jurada.backend.models.trips.GearCategory;
import edu.jurada.backend.repositories.trips.GearCategoryRepository;
import edu.jurada.backend.repositories.trips.GearRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GearControllerIT {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	GearRepository gearRepository;
	@Autowired
	GearCategoryRepository gearCategoryRepository;
	@Autowired
	ObjectMapper objectMapper;


	@Test
	void testThatGetGearCategoriesReturnsAllGearCategories() throws Exception {
		// given
		GearCategory gc1 = GearCategory.builder()
				.name("Cardio")
				.description("Exercises for strengthening the cardiovascular system and burning calories.")
				.build();


		gearCategoryRepository.save(gc1);
		GearCategory gc2 = GearCategory.builder()
				.name("Free weights")
				.description("Free weights that can be use for many different strength exercises")
				.build();
		gearCategoryRepository.save(gc2);

		//when
		MvcResult mvcResult = mockMvc.perform(get("/api/gearCategories")
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk()).andReturn();
		List<GearCategoryDto> gearCategories = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<GearCategoryDto>>() {
		});
		//then
		Set<Long> expected = Set.of(gc1.getId(), gc2.getId());
		Set<Long> actual = gearCategories.stream().map(GearCategoryDto::getId).collect(Collectors.toSet());
		assertEquals(expected,actual);
	}

	@Test
	void testThatGetGearsFromCategoryReturnsAllGearsFromCategory() throws Exception {

		// given
		GearCategory gc1 = GearCategory.builder()
				.name("Cardio")
				.description("Exercises for strengthening the cardiovascular system and burning calories.")
				.build();
		GearCategory gc2 = GearCategory.builder()
				.name("Free weights")
				.description("Free weights that can be use for many different strength exercises")
				.build();
		gearCategoryRepository.saveAll(List.of(gc1, gc2));
		Gear g1 = Gear.builder()
				.gearCategory(gc1)
				.name("Bicycle")
				.exerciseDescription("Cycling is a low impact cardio modality that does not interfere with strength training")
				.build();

		Gear g2 = Gear.builder()
				.gearCategory(gc1)
				.name("Treadmill")
				.exerciseDescription("Running on a treadmill is a great way to burn calories, however it is also hard on the joints")
				.build();

		Gear g3 = Gear.builder()
				.gearCategory(gc2)
				.name("Dumbbells")
				.build();
		gearRepository.saveAll(List.of(g1, g2, g3));
		// when
		MvcResult mvcResult = mockMvc.perform(get("/api/gearCategories/" + gc1.getId() + "/gear")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		List<Gear> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Gear>>() {});
		// then
		Set<Long> expected = Set.of(g1.getId(), g2.getId());
		Set<Long> actual = result.stream().map(Gear::getId).collect(Collectors.toSet());
		assertEquals(expected,actual);
	}
	@Test
	void testThatGetGearFromCategoryReturnsNotFoundWhenNoCategoryFound() throws Exception {
		mockMvc.perform(get("/api/gearCategories/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
