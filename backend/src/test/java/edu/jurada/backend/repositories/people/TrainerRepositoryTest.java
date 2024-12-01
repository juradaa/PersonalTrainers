package edu.jurada.backend.repositories.people;

import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.people.TrainerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TrainerRepositoryTest {
	@Autowired
	private TrainerRepository underTest;

	@Test
	void testThatSearchSeniorsFindsAndSortsTrainers(){
		// given
		Trainer ts1 = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Strength training")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("joe")
				.alias("john")
				.build();
		underTest.save(ts1);

		Trainer ts2 = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Hello there")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Joe Soe")
				.alias("soe")
				.build();
		underTest.save(ts2);

		Trainer ts3 = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Strength training")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Marry Joey")
				.alias("joey")
				.build();
		underTest.save(ts3);

		// when
		List<Trainer> trainers = underTest.searchSeniors("joe");

		// then
		assertEquals(3, trainers.size());
		assertEquals(ts1, trainers.get(0));
		assertEquals(ts3, trainers.get(1));
		assertEquals(ts2, trainers.get(2));
	}

	@Test
	void testThatCountSeniorsCountsSeniors(){
		Trainer ts1 = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Strength training")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("joe")
				.alias("john")
				.build();
		underTest.save(ts1);

		Trainer ts2 = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Hello there")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Joe Soe")
				.alias("soe")
				.build();
		underTest.save(ts2);

		Trainer ts3 = Trainer.builder()
				.type(TrainerType.ASSISTANT)
				.hireDate(LocalDate.parse("2020-12-12"))
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Marry Joey")
				.alias("joey")
				.build();
		underTest.save(ts3);

		// when
		Long count = underTest.countSeniors();
		assertEquals(2, count);
	}
}