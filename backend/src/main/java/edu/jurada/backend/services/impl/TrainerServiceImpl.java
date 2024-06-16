package edu.jurada.backend.services.impl;

import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.repositories.people.TrainerRepository;
import edu.jurada.backend.services.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

	private final TrainerRepository trainerRepository;

	@Override
	public List<Trainer> searchSeniorTrainers(String phrase) {
		return trainerRepository.searchSeniors(phrase);
	}

	@Override
	public boolean exists(long id) {
		return trainerRepository.existsById(id);
	}

	@Override
	public Optional<Trainer> findById(Long id) {
		return trainerRepository.findById(id);
	}

	@Override
	public Long countSeniors() {
		return trainerRepository.countSeniors();
	}
}
