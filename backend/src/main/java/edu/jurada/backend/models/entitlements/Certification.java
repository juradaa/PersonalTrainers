package edu.jurada.backend.models.entitlements;

import edu.jurada.backend.models.people.Trainer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
// no {bag} nor <<history>> so duplicate associations not allowed
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"certificate_id","trainer_id"})
})
public class Certification implements Comparable<Certification>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@PastOrPresent
	private LocalDate issueDate;
	private String issuerName;

	@ManyToOne
	@JoinColumn(name = "certificate_id", nullable = false)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@NotNull
	private Certificate certificate;

	@ManyToOne
	@JoinColumn(name = "trainer_id", nullable = false)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@NotNull
	private Trainer trainer;

	@Override
	public int compareTo(Certification c) {
		if (this.getIssueDate().isBefore(c.getIssueDate())) {
			return -1;
		}else if(this.getIssueDate().isAfter(c.getIssueDate())){
			return 1;
		}else{
			return 0;
		}
	}
}
