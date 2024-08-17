package com.localhost.main.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.localhost.main.Entities.Maker;
import com.localhost.main.controllers.dto.MakerDTO;
import com.localhost.main.services.IMakerService;

@RestController
@RequestMapping("/api/maker")
public class MakerController {
	@Autowired
	private IMakerService makerService;
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<Maker> makerOptional = makerService.findById(id);
		if(makerOptional.isPresent()) {
			Maker maker = makerOptional.get();
			
			MakerDTO makerDTO = MakerDTO.builder()
					.id(maker.getId())
					.name(maker.getName())
					.products(maker.getProducts())
					.build();
			return ResponseEntity.ok(makerDTO);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		List<MakerDTO> makerList = makerService.findAll()
				.stream()
				.map(maker -> MakerDTO.builder()
						.id(maker.getId())
						.name(maker.getName())
						.products(maker.getProducts())
						.build())
				.toList();
		return ResponseEntity.ok(makerList);
	}
	
	@PostMapping("/store")
	public ResponseEntity<?> store(@RequestBody MakerDTO makerDTO) throws URISyntaxException{
		if(makerDTO.getName().isBlank()) {
			return ResponseEntity.badRequest().build();
		}
		
		makerService.save(Maker.builder()
				.name(makerDTO.getName())
				.build());
		
		return ResponseEntity.created(new URI("/api/maker/store")).build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody MakerDTO makerDTO){
		Optional<Maker> makerOptional = makerService.findById(id);
		
		if(makerOptional.isPresent()) {
			Maker maker = makerOptional.get();
			maker.setName(makerDTO.getName());
			makerService.save(maker);
			return ResponseEntity.ok("registro actualizado");
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id){
		if(id != null) {
			makerService.deleteById(id);
			return ResponseEntity.ok("Registro eliminado");
		}
		
		return ResponseEntity.badRequest().build();
	}
}
