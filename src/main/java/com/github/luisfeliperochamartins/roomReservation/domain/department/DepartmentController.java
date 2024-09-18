package com.github.luisfeliperochamartins.roomReservation.domain.department;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "/admin/department")
public class DepartmentController {

	private final DepartmentRepository repository;

	@Autowired
	public DepartmentController(DepartmentRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<Department> insert(@RequestBody @Valid DepartmentRecord record, UriComponentsBuilder builder) {
		var department = repository.save(new Department(record.name()));

		var uri = builder.path("/api/admin/deparment").buildAndExpand(department.getId()).toUri();

		return ResponseEntity.created(uri).body(department);
	}

	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity udpate(@PathVariable Long id, @RequestBody DepartmentRecord record) {
		var department = repository.findById(id);

		if (department.isPresent()) {
			department.get().setName(record.name());
			return ResponseEntity.ok(department.get());
		}

		return ResponseEntity.notFound().build();
	}
}
