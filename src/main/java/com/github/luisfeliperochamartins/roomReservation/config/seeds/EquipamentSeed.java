package com.github.luisfeliperochamartins.roomReservation.config.seeds;

import com.github.luisfeliperochamartins.roomReservation.domain.equipament.Equipament;
import com.github.luisfeliperochamartins.roomReservation.domain.equipament.EquipamentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EquipamentSeed {

	@Bean
	CommandLineRunner runner(EquipamentRepository repository) {
		return args -> {
			var tv = new Equipament(1L, "TV");
			var projetor = new Equipament(2L, "Projetor");
			var laptop = new Equipament(3L, "Laptop");

			repository.saveAll(List.of(tv, projetor, laptop));
		};
	}
}
