package edu.jurada.backend.repositories.trips;

import edu.jurada.backend.models.trips.GearCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GearCategoryRepositoryTest {
	@Autowired
	private GearCategoryRepository underTest;

	@Test
	void testThatFindByIdWithGearFindsWhenPresent() {
		// given
		GearCategory gc1 = GearCategory.builder()
				.name("Cardio")
				.description("Exercises for strengthening the cardiovascular system and burning calories.")
				.build();
		underTest.save(gc1);

		// when
		Optional<GearCategory> gear = underTest.findByIdWithGear(gc1.getId());

		// then
		assertTrue(gear.isPresent());
		assertEquals(gc1, gear.get());
	}
}