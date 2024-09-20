package com.github.luisfeliperochamartins.roomReservation.domain.user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin/user")
public class UserAuthController {

	private final UserRepository repository;

	@Autowired
	public UserAuthController(UserRepository repository) {
		this.repository = repository;
	}

	@PatchMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<User> switchRole(@PathVariable Long id) {
		var user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

		user.switchRole();

		return ResponseEntity.ok(user);
	}
}
