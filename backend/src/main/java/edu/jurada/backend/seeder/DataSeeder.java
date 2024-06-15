package edu.jurada.backend.seeder;


import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.people.TrainerType;
import edu.jurada.backend.models.trips.Gear;
import edu.jurada.backend.models.trips.GearCategory;
import edu.jurada.backend.repositories.people.TrainerRepository;
import edu.jurada.backend.repositories.trips.GearCategoryRepository;
import edu.jurada.backend.repositories.trips.GearRepository;
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
	private final GearCategoryRepository gearCategoryRepository;
	private final GearRepository gearRepository;

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

		GearCategory gc1 = GearCategory.builder()
				.name("Cardio")
				.description("Ćwiczenia układu krążenia i spalanie kalorii.")
				.build();


		gearCategoryRepository.save(gc1);
		GearCategory gc2 = GearCategory.builder()
				.name("Ciężarki")
				.description("Wolne ciężarki, które można wykorzystać do wielu różnych ćwiczeń")
				.build();
		gearCategoryRepository.save(gc2);


		Gear g1 = Gear.builder()
				.gearCategory(gc1)
				.name("rower")
				.exerciseDescription("Pedałowanie")
				.build();

		Gear g2 = Gear.builder()
				.gearCategory(gc1)
				.name("Bieżnia")
				.exerciseDescription("Bieganie")
				.build();

		Gear g3 = Gear.builder()
				.gearCategory(gc2)
				.name("Hantle")
				.build();

		Gear g4 = Gear.builder()
				.gearCategory(gc2)
				.name("Sztangi")
				.build();
		gearRepository.saveAll(List.of(g1,g2,g3,g4));


	}
}
