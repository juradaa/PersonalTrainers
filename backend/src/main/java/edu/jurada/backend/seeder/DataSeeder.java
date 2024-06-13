package edu.jurada.backend.seeder;


import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.people.TrainerType;
import edu.jurada.backend.repositories.people.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder {
	private final TrainerRepository trainerRepository;

	@EventListener
	public void atStart(ContextRefreshedEvent event) {
		System.out.println("Hello there");
		Trainer ts1 = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Strength training")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Mary sue")
				.alias("marsue")
				.build();
		trainerRepository.save(ts1);

		Trainer ts2 = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Hello there")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("Joe soe")
				.alias("joesoe")
				.build();
		trainerRepository.save(ts2);

		Trainer ts3 = Trainer.builder()
				.type(TrainerType.SENIOR)
				.specialization("Strength training")
				.salary(new BigDecimal("6000"))
				.bio("A very good trainer")
				.name("John sue")
				.alias("joesoeu")
				.build();
		trainerRepository.save(ts3);
		List<Trainer> trainers = trainerRepository.searchSeniors("joesoe");
		System.out.println("trainers = " + trainers);
	}
}
