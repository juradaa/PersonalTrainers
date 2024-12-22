package edu.jurada.backend.repositories.people;

import edu.jurada.backend.models.people.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {

	@Query("from Trainer " +
			"where type = 'SENIOR' and (lower(name) like  lower(concat('%',:phrase,'%'))" +
			" or lower(alias) like lower(concat('%',:phrase,'%'))) " +
			"order by " +
			"case when lower(:phrase) = lower(alias) or lower(:phrase) = lower(name) then 0 " +
			"else case when lower(alias) like lower(concat('%', :phrase,'%')) then 1 " +
			"else 2 end end limit 10")
	List<Trainer> searchSeniors(String phrase);

	@Query("select count(*) from Trainer where type = 'SENIOR'")
	Long countSeniors();
}
