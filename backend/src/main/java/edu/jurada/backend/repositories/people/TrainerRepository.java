package edu.jurada.backend.repositories.people;

import edu.jurada.backend.models.people.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {

	@Query("from Trainer where type = 'SENIOR' and (name like  concat('%',:phrase,'%') or alias like concat('%',:phrase,'%')) order by case when concat('%', :phrase,'%') = alias or concat('%', :phrase,'%') = name then 0 else case when alias like concat('%', :phrase,'%')then 1 else 2 end end limit 10")
	List<Trainer> searchSeniors(String phrase);
}
