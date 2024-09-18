package com.github.luisfeliperochamartins.roomReservation.config.security;

import com.github.luisfeliperochamartins.roomReservation.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	private final TokenService tokenService;
	private final UserRepository repository;

	@Autowired
	public SecurityFilter(TokenService token, UserRepository repository) {
		this.tokenService = token;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var token = getTokenFromHeader(request);

		if (token != null) {
			var subject = tokenService.getSubject(token);
			var usuario = repository.findByUsername(subject);

			if (usuario.isPresent()) {
				var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.get().getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);
	}

	private String getTokenFromHeader(HttpServletRequest request) {
		var auth = request.getHeader("Authorization");

		if (auth != null && auth.startsWith("Bearer ")) {
			return auth.replace("Bearer ", "");
		}
		return null;
	}
}
