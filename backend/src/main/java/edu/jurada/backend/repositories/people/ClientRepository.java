package edu.jurada.backend.repositories.people;

import edu.jurada.backend.models.people.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}