package edu.jurada.backend.models.entitlements;


import edu.jurada.backend.models.entitlements.comparators.ByIssueDateComparator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.SortComparator;
import org.hibernate.annotations.SortNatural;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String institution;

	@OneToMany(mappedBy = "certificate", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	@SortComparator(ByIssueDateComparator.class)
	private SortedSet<Certification> certifications = new TreeSet<>(new ByIssueDateComparator());


}
