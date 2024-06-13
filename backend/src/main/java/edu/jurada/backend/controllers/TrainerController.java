package edu.jurada.backend.controllers;

import edu.jurada.backend.models.dto.people.SimpleTrainerViewDto;
import edu.jurada.backend.models.people.Trainer;
import edu.jurada.backend.services.TrainerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/trainers")
@RequiredArgsConstructor
public class TrainerController {

	private final TrainerService trainerService;
	private final ModelMapper modelMapper;

	@GetMapping("/seniors")
	public ResponseEntity<List<SimpleTrainerViewDto>> search(@RequestParam String searched){
		List<Trainer> trainers = trainerService.searchSeniorTrainers(searched);
		List<SimpleTrainerViewDto> trainerDtos = trainers.stream().map((element) -> modelMapper.map(element, SimpleTrainerViewDto.class)).toList();
		return new ResponseEntity<>(trainerDtos, HttpStatus.OK);
	}



}
