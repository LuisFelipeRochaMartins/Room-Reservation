package com.github.luisfeliperochamartins.roomReservation.domain.equipament;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "/api/equipament")
public class EquipamentController {

	private final EquipamentRepository repository;

	@Autowired
	public EquipamentController(EquipamentRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<Equipament> insert(@RequestBody @Valid EquipamentRecord record, UriComponentsBuilder builder) {
		var equipament = repository.save(new Equipament(record.name()));

		var uri = builder.path("/api/equipament/{id}").buildAndExpand(equipament.getId()).toUri();
		return ResponseEntity.created(uri).body(equipament);
	}

	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<Equipament> insert(@PathVariable Long id, @RequestBody @Valid EquipamentRecord record) {
		var equipament = repository.findById(id);

		if (equipament.isPresent()) {
			equipament.get().setName(record.name());
			return ResponseEntity.ok(equipament.get());
		}

		return ResponseEntity.notFound().build();
	}

}
