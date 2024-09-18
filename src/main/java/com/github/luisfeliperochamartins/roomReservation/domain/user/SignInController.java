package com.github.luisfeliperochamartins.roomReservation.domain.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user/sign-in")
public class SignInController {

	private final UserRepository repository;

	@Autowired
	public SignInController(UserRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity register(@RequestBody @Valid UserRecord user) {
		var usuario = repository.save(new User(user));

		return ResponseEntity.ok(usuario);
	}
}
