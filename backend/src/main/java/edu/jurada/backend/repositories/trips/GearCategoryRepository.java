package edu.jurada.backend.repositories.trips;

import edu.jurada.backend.models.trips.GearCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GearCategoryRepository extends JpaRepository<GearCategory, Long> {

	@Query("from GearCategory gc left join fetch gc.gearSet where gc.id = :id")
	Optional<GearCategory> findByIdWithGear(long id);
}
