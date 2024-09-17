package com.github.luisfeliperochamartins.roomReservation.config.seeds;

import com.github.luisfeliperochamartins.roomReservation.domain.department.Department;
import com.github.luisfeliperochamartins.roomReservation.domain.department.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DepartmentSeed {

	@Bean
	CommandLineRunner seeding(DepartmentRepository repository) {
		return args -> {
			var logistics = new Department(1L, "Logistics");
			var administrative = new Department(2L, "Administrative");
			var quality = new Department(3L, "Quality");

			repository.saveAll(List.of(logistics, administrative, quality));
		};
	}
}
