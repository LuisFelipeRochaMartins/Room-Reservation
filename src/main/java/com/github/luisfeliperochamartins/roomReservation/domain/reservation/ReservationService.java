package com.github.luisfeliperochamartins.roomReservation.domain.reservation;

import com.github.luisfeliperochamartins.roomReservation.config.errors.BusinessError;
import com.github.luisfeliperochamartins.roomReservation.domain.room.Room;
import com.github.luisfeliperochamartins.roomReservation.domain.room.RoomRepository;
import com.github.luisfeliperochamartins.roomReservation.domain.user.User;
import com.github.luisfeliperochamartins.roomReservation.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final RoomRepository roomRepository;
	private final UserRepository userRepository;

	@Autowired
	public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository,
	                          UserRepository userRepository) {
		this.reservationRepository = reservationRepository;
		this.roomRepository = roomRepository;
		this.userRepository = userRepository;
	}

	public Reservation insert(ReservationRecord record) {
		var room = this.getRoomById(record.roomId());
		var users = this.getUsersByIds(record.userIds());

		if (users.size() > room.getCapacity()) {
			throw new BusinessError("Essa sala não suporta todas as pessoas");
		}

		var reservation = new Reservation(null, room, users, record.startAt(), record.endAt());
		return reservationRepository.save(reservation);
	}

	private Room getRoomById(Long id) {
		return roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sala não encontrada"));
	}

	private List<User> getUsersByIds(List<Long> ids) {
		var users = userRepository.findAllById(ids);

		if (users.size() != ids.size()) {
			throw new EntityNotFoundException("Alguns dos usuários não foram encontrados");
		}

		return users;
	}

	public Reservation update(Long id, ReservationRecord record) {
		var reservation = reservationRepository.findById(id).orElseThrow(() ->
				new EntityNotFoundException("Sala não encontrada")
		);
		var room = this.getRoomById(record.roomId());
		var users = getUsersByIds(record.userIds());

		reservation.update(room, users, record.startAt(), record.endAt());

		return reservation;
	}
}
