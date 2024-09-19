package com.github.luisfeliperochamartins.roomReservation.config.seeds;

import com.github.luisfeliperochamartins.roomReservation.domain.user.Role;
import com.github.luisfeliperochamartins.roomReservation.domain.user.User;
import com.github.luisfeliperochamartins.roomReservation.domain.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class UserSeed {

	@Bean
	CommandLineRunner userSeed(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			var user = new User("admin@admin.com", encoder.encode("1234"), Role.ADMIN);
			userRepository.save(user);
		};
	}
}
