package edu.jurada.backend.services;

import edu.jurada.backend.models.trips.Gear;
import edu.jurada.backend.models.trips.GearCategory;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface GearService {
	/**
	 * Gets all GearCategories from the repository and loads eagerly the associated gear.
	 * @return list of Gear
	 */
	public List<GearCategory> getAllGearCategories();

	/**
	 * Gets GearCategory with the specified id and loads eagerly associated Gear.
	 * @param id id of the GearCategory to fetch
	 * @return GearCategory with gear
	 */

	public Optional<GearCategory> getWithGear(long id);

	/**
	 * Finds all the gear that has an id that is in the ids list.
	 * @param ids - list of ids the gears that will be fetched
	 * @return list of found gears
	 */
	public List<Gear> findAllById(List<Long> ids);

}
