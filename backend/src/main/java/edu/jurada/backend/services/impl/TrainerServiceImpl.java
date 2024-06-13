package edu.jurada.backend.services.impl;

import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.repositories.people.TrainerRepository;
import edu.jurada.backend.services.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

	private final TrainerRepository trainerRepository;

	@Override
	public List<Trainer> searchSeniorTrainers(String phrase) {
		return trainerRepository.searchSeniors(phrase);
	}
}
