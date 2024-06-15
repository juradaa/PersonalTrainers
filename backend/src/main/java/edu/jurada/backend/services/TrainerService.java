package edu.jurada.backend.services;

import edu.jurada.backend.models.people.Trainer;

import java.util.List;

public interface TrainerService {

	List<Trainer> searchSeniorTrainers(String phrase);
	boolean exists(long id);
}
