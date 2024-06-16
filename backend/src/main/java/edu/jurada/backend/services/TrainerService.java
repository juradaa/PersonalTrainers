package edu.jurada.backend.services;

import edu.jurada.backend.models.people.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerService {

	/**
	 * Searches all the trainers by name and alias. Exact matches are first, then matches to the alias and then
	 * the name. Fetches only top 10 matching trainers
	 * @param phrase the string to be used for searching
	 * @return list of trainers that matched the phrase
	 */
	List<Trainer> searchSeniorTrainers(String phrase);

	/**
	 * Checks whether a trainer with such an id exists
	 * @param id id of the trainer
	 * @return whether the trainer exists
	 */
	boolean exists(long id);

	/**
	 * Selects the trainer and returns if found and an empty optional otherwise
	 * @param id id of the trainer
	 * @return Optional of the trainer searched for
	 */

	Optional<Trainer> findById(Long id);
	Long countSeniors();
}
