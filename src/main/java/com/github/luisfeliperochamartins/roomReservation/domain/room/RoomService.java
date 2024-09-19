package com.github.luisfeliperochamartins.roomReservation.domain.room;

import com.github.luisfeliperochamartins.roomReservation.config.errors.BusinessError;
import com.github.luisfeliperochamartins.roomReservation.domain.equipament.Equipament;
import com.github.luisfeliperochamartins.roomReservation.domain.equipament.EquipamentRepository;
import com.github.luisfeliperochamartins.roomReservation.domain.reservation.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

	private final RoomRepository roomRepository;
	private final EquipamentRepository equipamentRepository;

	@Autowired
	public RoomService(RoomRepository roomRepository, EquipamentRepository equipamentRepository) {
		this.roomRepository = roomRepository;
		this.equipamentRepository = equipamentRepository;
	}

	public Room insert(RoomRecord record) {
		var equipaments = this.getEquipamentsByIds(record.equipamentsIds());
		var room = new Room(record.name(), record.capacity(), equipaments, null);

		return roomRepository.save(room);
	}

	private List<Equipament> getEquipamentsByIds(List<Long> ids) {
		var equipaments = equipamentRepository.findAllById(ids);

		if (ids.size() > equipaments.size()) {
			throw new EntityNotFoundException("Alguns equipamentos não foram encontrados");
		}

		return equipaments;
	}
}
