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
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
@Profile({"dev", "prod"})
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

		Gear g4 = Gear.builder()
				.gearCategory(gc2)
				.name("Barbells")
				.build();
		gearRepository.saveAll(List.of(g1, g2, g3, g4));

		TrainingTrip tt1 = TrainingTrip.builder()
				.trainer(ts2)
				.name("Calisthenics")
				.destination("Egypt")
				.description("Body-weight exercises for strength and hypertrophy")
				.topics(Set.of("Strength training", "Hypertrophic training"))
				.startDate(LocalDate.parse("2024-06-15"))
				.endDate(LocalDate.parse("2024-06-27"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();


		TrainingTrip tt2 = TrainingTrip.builder()
				.name("Cardio")
				.trainer(ts2)
				.destination("London")
				.description("Training for weight-loss and cardiovascular health")
				.topics(Set.of("weight-loss", "cardiovascular health"))
				.startDate(LocalDate.parse("2024-07-15"))
				.endDate(LocalDate.parse("2024-07-24"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();

		TrainingTrip tt3 = TrainingTrip.builder()
				.name("Training in the gym")
				.trainer(ts2)
				.destination("Warsaw")
				.description("Utilising free weights and machines in training")
				.topics(Set.of("Muscle anatomy", "Latest research on lengthened partials"))
				.startDate(LocalDate.parse("2024-06-25"))
				.endDate(LocalDate.parse("2024-07-07"))
				.baseStatus(TripStatus.PUBLISHED)
				.build();

		trainingTripRepository.saveAll(List.of(tt1, tt2, tt3));

		Certificate c1 = Certificate.builder()
				.name("Hypertrophy I")
				.institution("Enlightenment periodization")
				.build();
		Certificate c2 = Certificate.builder()
				.name("Weightloss II+")
				.institution("San Escobar academy of Sciences")
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
				.cuisineDescription("A well balanced diet for vegans.")
				.name("Vegan diet + gym for busy people.")
				.description("A plan that provides both the dietary and training resources for people at the beginning of their fitness journey")
				.cost(BigDecimal.ONE)
				.build();
		subscriptionRepository.save(subscription);

		Client cl1 = Client.builder().creditCardNumber("4539135733660738")
				.weight(70)
				.height(1.80)
				.goal("Muscular hypertrophy")
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
