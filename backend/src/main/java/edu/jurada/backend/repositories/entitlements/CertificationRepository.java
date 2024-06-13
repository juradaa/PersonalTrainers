package edu.jurada.backend.repositories.entitlements;

import edu.jurada.backend.models.entitlements.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

	@Query("from Certification order by issueDate")
	List<Certification> findAll();
}
