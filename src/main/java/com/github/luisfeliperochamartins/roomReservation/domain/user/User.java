package com.github.luisfeliperochamartins.roomReservation.domain.user;

import com.github.luisfeliperochamartins.roomReservation.domain.department.Department;
import com.github.luisfeliperochamartins.roomReservation.domain.reservation.Reservation;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;
	private String password;
	private Boolean status = true;

	@Enumerated(EnumType.STRING)
	private Role role;

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

	public User() {}

	public User(Long id, String username, String password, Boolean status, Role role, Department department, List<Reservation> reservations) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.status = status;
		this.role = role;
		this.department = department;
		this.reservations = reservations;
	}

	public User(UserRecord user) {
		this.username = user.username();
		this.password = user.password();
		this.role = Role.FUNCIONARIO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(role.name()));
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("id = ").append(id);
		sb.append(", username = ").append(username);
		sb.append(", password = ").append(password);
		sb.append(", status = ").append(status);
		sb.append(", department = ").append(department);
		sb.append(", reservations = ").append(reservations);
		return sb.toString();
	}
}
