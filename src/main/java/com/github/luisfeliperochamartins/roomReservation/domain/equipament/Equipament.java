package com.github.luisfeliperochamartins.roomReservation.domain.equipament;

import jakarta.persistence.*;

@Entity
@Table(name = "equipaments")
public class Equipament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	public Equipament() {}

	public Equipament(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("id = ").append(id);
		sb.append(", nome = ").append(nome);
		return sb.toString();
	}
}
