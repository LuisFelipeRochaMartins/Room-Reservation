package com.github.luisfeliperochamartins.roomReservation.domain.room;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "api/room")
public class RoomController {

	private final RoomRepository repository;
	private final RoomService service;

	@Autowired
	public RoomController(RoomRepository repository, RoomService service) {
		this.repository = repository;
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Room>> getAll() {
		var rooms = repository.findAll();

		return ResponseEntity.ok(rooms);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Room> getById(@PathVariable Long id) {
		var room = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sala não encontrada!"));

		return ResponseEntity.ok(room);
	}

	@PostMapping
	public ResponseEntity<Room> insert(@RequestBody @Valid RoomRecord record, UriComponentsBuilder builder) {
		var room = service.insert(record);

		var uri = builder.path("/api/room/{id}").buildAndExpand(room.getId()).toUri();

		return ResponseEntity.created(uri).body(room);
	}
}
