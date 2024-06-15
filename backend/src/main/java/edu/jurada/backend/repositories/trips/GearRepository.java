package edu.jurada.backend.repositories.trips;

import edu.jurada.backend.models.trips.Gear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GearRepository extends JpaRepository<Gear,Long> {
	@Query("from Gear where id in (:ids)")
	public List<Gear> findAllByIds(List<Long> ids);
}
