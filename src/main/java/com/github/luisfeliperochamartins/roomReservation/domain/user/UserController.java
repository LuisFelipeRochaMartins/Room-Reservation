package com.github.luisfeliperochamartins.roomReservation.domain.user;

import com.github.luisfeliperochamartins.roomReservation.config.security.Token;
import com.github.luisfeliperochamartins.roomReservation.config.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

	private final AuthenticationManager manager;
	private final TokenService service;

	@Autowired
	public UserController(AuthenticationManager manager, TokenService service) {
		this.manager = manager;
		this.service = service;
	}

	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody @Valid UserRecord user) {
		var authToken = new UsernamePasswordAuthenticationToken(user.username(), user.password());
		var auth = manager.authenticate(authToken);

		var token = service.generateToken((User) auth.getPrincipal());

		return ResponseEntity.ok(new Token(token));
	}
}
