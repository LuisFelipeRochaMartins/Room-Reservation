package com.github.luisfeliperochamartins.roomReservation.domain.reservation;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "api/reservations")
public class ReservationController {

	private final ReservationService service;

	@Autowired
	public ReservationController(ReservationService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Reservation> insert(@RequestBody @Valid ReservationRecord record, UriComponentsBuilder builder) {
		var reservation = service.insert(record);

		var uri = builder.path("/api/reservations/{id}").buildAndExpand(reservation.getId()).toUri();

		return ResponseEntity.created(uri).body(reservation);
	}

	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<Reservation> update(@PathVariable Long id, @RequestBody @Valid ReservationRecord record) {
		var reservation = service.update(id, record);

		return ResponseEntity.ok(reservation);
	}
}
