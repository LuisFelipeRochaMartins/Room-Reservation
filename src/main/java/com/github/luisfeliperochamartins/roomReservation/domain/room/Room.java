package com.github.luisfeliperochamartins.roomReservation.domain.room;

import com.github.luisfeliperochamartins.roomReservation.domain.equipament.Equipament;
import com.github.luisfeliperochamartins.roomReservation.domain.reservation.Reservation;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer capacity;
	@ManyToMany
	@JoinTable(
			name = "room_equipament",
			joinColumns = @JoinColumn(name = "room_id"),
			inverseJoinColumns = @JoinColumn(name = "equipament_id")
	)
	private List<Equipament> equipaments;

	@OneToMany(mappedBy = "room")
	private List<Reservation> reservations;

	public Room() {}

	public Room(Long id, String name, Integer capacity, List<Equipament> equipaments, List<Reservation> reservations) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.equipaments = equipaments;
		this.reservations = reservations;
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

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public List<Equipament> getEquipaments() {
		return equipaments;
	}

	public void setEquipaments(List<Equipament> equipaments) {
		this.equipaments = equipaments;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("id = ").append(id);
		sb.append(", name = ").append(name);
		sb.append(", capacity = ").append(capacity);
		sb.append(", equipaments = ").append(equipaments);
		return sb.toString();
	}
}
