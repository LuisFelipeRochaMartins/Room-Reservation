package com.github.luisfeliperochamartins.roomReservation.domain.room;

import com.github.luisfeliperochamartins.roomReservation.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
