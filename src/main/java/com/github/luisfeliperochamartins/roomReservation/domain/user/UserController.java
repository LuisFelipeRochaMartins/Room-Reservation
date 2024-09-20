package com.github.luisfeliperochamartins.roomReservation.domain.user;

import com.github.luisfeliperochamartins.roomReservation.config.security.Token;
import com.github.luisfeliperochamartins.roomReservation.config.security.TokenService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

	private final AuthenticationManager manager;
	private final TokenService service;
	private final UserRepository repository;

	@Autowired
	public UserController(AuthenticationManager manager, TokenService service,
	                      UserRepository repository) {
		this.manager = manager;
		this.service = service;
		this.repository = repository;
	}

	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody @Valid UserRecord user) {
		var authToken = new UsernamePasswordAuthenticationToken(user.username(), user.password());
		var auth = manager.authenticate(authToken);

		var token = service.generateToken((User) auth.getPrincipal());

		return ResponseEntity.ok(new Token(token));
	}
}
