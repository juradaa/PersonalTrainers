package edu.jurada.backend.models.entitlements;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
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

	//TODO: consider orphan removal
	@OneToMany(mappedBy = "certificate", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	@SortNatural //because ordered
	private SortedSet<Certification> certifications = new TreeSet<>();


}
