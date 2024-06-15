package edu.jurada.backend.repositories.trips;

import edu.jurada.backend.models.trips.Gear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GearRepository extends JpaRepository<Gear,Long> {
}
