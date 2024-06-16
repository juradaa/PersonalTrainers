package edu.jurada.backend.repositories.entitlements;

import edu.jurada.backend.models.entitlements.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
