package com.github.luisfeliperochamartins.roomReservation.domain.employee;

import com.github.luisfeliperochamartins.roomReservation.domain.department.Department;
import com.github.luisfeliperochamartins.roomReservation.domain.reservation.Reservation;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToMany
	@JoinTable(
			name = "employee_reservation",
			joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "reservation_id")
	)
	private List<Reservation> reservations;

	public Employee() {}

	public Employee(Long id, String email, Department department, List<Reservation> reservations) {
		this.id = id;
		this.email = email;
		this.department = department;
		this.reservations = reservations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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
		sb.append(", email = ").append(email);
		sb.append(", department = ").append(department);
		return sb.toString();
	}
}
