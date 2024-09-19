package com.github.luisfeliperochamartins.roomReservation.domain.reservation;

import com.github.luisfeliperochamartins.roomReservation.domain.user.User;
import com.github.luisfeliperochamartins.roomReservation.domain.room.Room;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	@ManyToMany
	@JoinTable(
			name = "employee_reservations",
			joinColumns = @JoinColumn(name = "reservation_id"),
			inverseJoinColumns = @JoinColumn(name = "employee_id")
	)
	private List<User> users;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public Reservation() {}

	public Reservation(Long id, Room room, List<User> users, LocalDateTime startDate, LocalDateTime endDate) {
		this.id = id;
		this.room = room;
		this.users = users;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<User> getEmployees() {
		return users;
	}

	public void setEmployees(List<User> users) {
		this.users = users;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("id = ").append(id);
		sb.append(", room = ").append(room);
		sb.append(", employees = ").append(users);
		sb.append(", startDate = ").append(startDate);
		sb.append(", endDate = ").append(endDate);
		return sb.toString();
	}

	public void update(Room room, List<User> users, LocalDateTime startAt, LocalDateTime endAt) {
		if (room != null) {
			this.room = room;
		}

		if (users != null) {
			this.users = users;
		}

		if (startAt != null) {
			this.startDate = startAt;
		}

		if (endAt != null) {
			this.endDate = startDate;
		}
	}
}
