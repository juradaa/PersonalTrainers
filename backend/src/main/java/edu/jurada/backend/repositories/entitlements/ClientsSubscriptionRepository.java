package edu.jurada.backend.repositories.entitlements;

import edu.jurada.backend.models.entitlements.ClientsSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsSubscriptionRepository extends JpaRepository<ClientsSubscription, Long> {
}
