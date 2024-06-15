package edu.jurada.backend.services;

import edu.jurada.backend.models.trips.Gear;
import edu.jurada.backend.models.trips.GearCategory;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface GearService {
	public List<GearCategory> getAllGearCategories();
	public Optional<GearCategory> getWithGear(long id);

}
