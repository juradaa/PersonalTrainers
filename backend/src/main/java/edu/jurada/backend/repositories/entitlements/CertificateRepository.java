package edu.jurada.backend.repositories.entitlements;

import edu.jurada.backend.models.entitlements.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
