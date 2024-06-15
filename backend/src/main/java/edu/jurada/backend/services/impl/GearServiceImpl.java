package edu.jurada.backend.services.impl;

import edu.jurada.backend.models.trips.GearCategory;
import edu.jurada.backend.repositories.trips.GearCategoryRepository;
import edu.jurada.backend.services.GearService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GearServiceImpl implements GearService {

	private final GearCategoryRepository gearCategoryRepository;
	@Override
	public List<GearCategory> getAllGearCategories() {
		return gearCategoryRepository.findAll();
	}

	@Override
	public Optional<GearCategory> getWithGear(long id) {
		return gearCategoryRepository.findByIdWithGear(id);
	}
}
