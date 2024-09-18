package com.github.luisfeliperochamartins.roomReservation.domain.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "/api/user/sign-in")
public class SignInController {

	private final UserRepository repository;
	private final PasswordEncoder encoder;

	@Autowired
	public SignInController(UserRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	@PostMapping
	public ResponseEntity<?> register(@RequestBody @Valid UserRecord record, UriComponentsBuilder builder) {
		var encodedPassword = encoder.encode(record.password());
		User usuario;

		if (record.role() != null) {
			usuario = repository.save(new User(record.username(), encodedPassword, Role.FUNCIONARIO));
		} else {
			usuario = repository.save(new User(record.username(), encodedPassword));
		}

		var uri = builder.path("/api/user/sign-in").buildAndExpand(usuario.getId()).toUri();

		return ResponseEntity.created(uri).body(usuario);
	}
}
