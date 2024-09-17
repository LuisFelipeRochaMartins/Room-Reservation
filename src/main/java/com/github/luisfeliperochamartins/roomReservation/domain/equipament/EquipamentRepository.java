package com.github.luisfeliperochamartins.roomReservation.domain.equipament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentRepository extends JpaRepository<Equipament, Long> {
}
