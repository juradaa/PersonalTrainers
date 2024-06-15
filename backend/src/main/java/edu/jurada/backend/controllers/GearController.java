package edu.jurada.backend.controllers;

import edu.jurada.backend.models.dto.trips.GearCategoryDto;
import edu.jurada.backend.models.dto.trips.GearDto;
import edu.jurada.backend.models.trips.Gear;
import edu.jurada.backend.models.trips.GearCategory;
import edu.jurada.backend.services.GearService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gearCategories")
@RequiredArgsConstructor
public class GearController {
	private final GearService gearService;
	private final ModelMapper modelMapper;




	Logger logger = LoggerFactory.getLogger(GearController.class);
	@GetMapping
	public ResponseEntity<List<GearCategoryDto>> gearCategoryDtoList() {
		List<GearCategory> categories = gearService.getAllGearCategories();
		List<GearCategoryDto> dtos = categories.stream().map((element) -> modelMapper.map(element, GearCategoryDto.class)).collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping("{id}/gear")
	public ResponseEntity<List<GearDto>> getGearsCategory(@PathVariable("id") long id){
		Optional<GearCategory> withGear = gearService.getWithGear(id);
		if(withGear.isEmpty()){
			System.out.println("id = " + id);
			return ResponseEntity.notFound().build();
		}
		Set<Gear> gearSet = withGear.get().getGearSet();
		List<GearDto> dtos = gearSet.stream().map((element) -> modelMapper.map(element, GearDto.class)).collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
