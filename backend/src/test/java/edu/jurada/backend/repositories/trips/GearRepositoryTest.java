package edu.jurada.backend.repositories.trips;

import edu.jurada.backend.models.trips.Gear;
import edu.jurada.backend.models.trips.GearCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GearRepositoryTest {
	@Autowired
	private GearRepository underTest;
	@Autowired
	private GearCategoryRepository gearCategoryRepository;
	@Test
	void testThatFindAllByIdsFindsAll(){
		//given
		GearCategory gc1 = GearCategory.builder()
				.name("Cardio")
				.description("Exercises for strengthening the cardiovascular system and burning calories.")
				.build();

		gearCategoryRepository.save(gc1);
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
				.gearCategory(gc1)
				.name("Dumbbells")
				.build();
		underTest.saveAll(List.of(g1,g2,g3));

		//when
		List<Gear> gears = underTest.findAllByIds(List.of(g1.getId(),g2.getId()));
		assertEquals(2, gears.size());
		assertTrue(gears.contains(g1));
		assertTrue(gears.contains(g2));

	}

}