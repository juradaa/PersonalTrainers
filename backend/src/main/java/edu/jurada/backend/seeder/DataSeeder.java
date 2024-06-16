package edu.jurada.backend.seeder;


import edu.jurada.backend.models.entitlements.*;
import edu.jurada.backend.models.people.Client;
import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.models.people.TrainerType;
import edu.jurada.backend.models.trips.Gear;
import edu.jurada.backend.models.trips.GearCategory;
import edu.jurada.backend.models.trips.TrainingTrip;
import edu.jurada.backend.models.trips.TripStatus;
import edu.jurada.backend.repositories.entitlements.CertificateRepository;
import edu.jurada.backend.repositories.entitlements.CertificationRepository;
import edu.jurada.backend.repositories.entitlements.ClientsSubscriptionRepository;
import edu.jurada.backend.repositories.entitlements.SubscriptionRepository;
import edu.jurada.backend.repositories.people.ClientRepository;
import edu.jurada.backend.repositories.people.TrainerRepository;
import edu.jurada.backend.repositories.trips.GearCategoryRepository;
import edu.jurada.backend.repositories.trips.GearRepository;
import edu.jurada.backend.repositories.trips.TrainingTripRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder {
	private final TrainerRepository trainerRepository;
	private final GearCategoryRepository gearCategoryRepository;
	private final GearRepository gearRepository;
	private final TrainingTripRepository trainingTripRepository;

	private final CertificationRepository certificationRepository;
	private final CertificateRepository certificateRepository;


	Logger logger = LoggerFactory.getLogger(DataSeeder.class);
	private final SubscriptionRepository subscriptionRepository;
	private final ClientRepository clientRepository;
	private final ClientsSubscriptionRepository clientsSubscriptionRepository;

	@EventListener
	public void atStart(ContextRefreshedEvent event) {
		if (gearRepository.count() > 0) {
			logger.trace("Database not empty. Seeding aborted.");
			return;
		}
		logger.trace("Seeding the database...");
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
		gearRepository.saveAll(List.of(g1, g2, g3, g4));

		TrainingTrip tt1 = TrainingTrip.builder()
				.trainer(ts2)
				.name("Kalistenika")
				.destination("Sosnowiec")
				.description("Ćwiczenia z masą własnego ciała w celu zwiększenia masy mieśni oraz siły")
				.topics(Set.of("Siła", "Masa"))
				.startDate(LocalDate.parse("2024-06-15"))
				.endDate(LocalDate.parse("2024-06-27"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();


		TrainingTrip tt2 = TrainingTrip.builder()
				.name("Cardio")
				.trainer(ts2)
				.destination("Sosnowiec")
				.description("Ćwiczenia z masą własnego ciała w celu zwiększenia masy mieśni oraz siły")
				.topics(Set.of("Siła", "Masa"))
				.startDate(LocalDate.parse("2024-07-15"))
				.endDate(LocalDate.parse("2024-07-24"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();

		TrainingTrip tt3 = TrainingTrip.builder()
				.name("Siłownia")
				.trainer(ts2)
				.destination("Sosnowiec")
				.description("Ćwiczenia z masą własnego ciała w celu zwiększenia masy mieśni oraz siły")
				.topics(Set.of("Siła", "Masa"))
				.startDate(LocalDate.parse("2024-06-25"))
				.endDate(LocalDate.parse("2024-07-07"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();

		trainingTripRepository.saveAll(List.of(tt1, tt2, tt3));

		Certificate c1 = Certificate.builder()
				.name("Hipertrofia")
				.institution("Akademia Nauk Sportowych")
				.build();
		Certificate c2 = Certificate.builder()
				.name("Układ krążeniowy człowieka III")
				.institution("Polskie towarzystwo kardiologiczne")
				.build();

		certificateRepository.saveAll(List.of(c1, c2));

		Certification cn1 = Certification.builder()
				.issueDate(LocalDate.parse("2020-01-11"))
				.trainer(ts1)
				.certificate(c1)
				.build();
		c1.getCertifications().add(cn1);
		ts1.getCertifications().add(cn1);

		Certification cn2 = Certification.builder()
				.issueDate(LocalDate.parse("2019-01-11"))
				.trainer(ts1)
				.certificate(c2)
				.build();
		c2.getCertifications().add(cn2);
		ts1.getCertifications().add(cn2);
		certificationRepository.saveAll(List.of(cn1, cn2));
		logger.trace(ts1.getCertifications().toString());
		Subscription subscription = Subscription.builder()
				.subscriptionTypes(Set.of(SubcriptionType.GYM, SubcriptionType.DIET))
				.entrancesPerWeek(3)
				.hoursWithTrainer(1)
				.dailyCalorieCount(2000)
				.dietaryRestrictions(Set.of("Vegan", "Lactose Intolerant"))
				.cuisineDescription("Good i guess")
				.name("Pełny plan dieta + siłownia dla zabieganychj")
				.description("Plan łączący dietę i 3 godziny w siłowni dla osób zaczynających przygodę z fitnessem")
				.cost(BigDecimal.ONE)
				.build();
		subscriptionRepository.save(subscription);

		Client cl1 = Client.builder().creditCardNumber("4539135733660738")
				.weight(70)
				.height(1.80)
				.goal("Rozwój mięśni")
				.name("Marek Tarek")
				.alias("matar")
				.build();
		clientRepository.save(cl1);
		ClientsSubscription cs = ClientsSubscription.builder()
				.startDate(LocalDate.parse("2023-11-11"))
				.endDate(LocalDate.parse("2024-11-11"))
				.isPaid(true)
				.subscription(subscription)
				.client(cl1)
				.build();
		clientsSubscriptionRepository.save(cs);

		logger.trace("Seeding the database completed");

	}
}
