package com.github.luisfeliperochamartins.roomReservation.domain.equipament;

import jakarta.persistence.*;

@Entity
@Table(name = "equipaments")
public class Equipament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public Equipament() {}

	public Equipament(Long id, String nome) {
		this.id = id;
		this.name = nome;
	}

	public Equipament(String name) {
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

	public void setName(String nome) {
		this.name = nome;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("id = ").append(id);
		sb.append(", nome = ").append(name);
		return sb.toString();
	}
}
