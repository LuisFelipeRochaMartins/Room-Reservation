package com.github.luisfeliperochamartins.roomReservation.domain.department;

import com.github.luisfeliperochamartins.roomReservation.domain.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "department")
	private List<User> users;

	public Department() {}

	public Department(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Department(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("id = ").append(id);
		sb.append(", name = ").append(name);
		return sb.toString();
	}
}
