package com.github.luisfeliperochamartins.roomReservation.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.github.luisfeliperochamartins.roomReservation.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

	@Value("${api.secret.token.jwt}")
	private String secret;

	public String generateToken(User usuario) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("medical.clini")
					.withSubject(usuario.getUsername())
					.withExpiresAt(dataExpiracao())
					.sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error to generate JWT token", exception);
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

	public String getSubject(String tokenJWT) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("medical.clini")
					.build()
					.verify(tokenJWT)
					.getSubject();
		} catch (JWTCreationException exception){
			throw new RuntimeException("Token JWT inválido ou inspirado!");
		}
	}
}
