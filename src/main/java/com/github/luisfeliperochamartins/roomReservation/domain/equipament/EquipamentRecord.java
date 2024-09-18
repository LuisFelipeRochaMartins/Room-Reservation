package com.github.luisfeliperochamartins.roomReservation.domain.equipament;

import jakarta.validation.constraints.NotBlank;

public record EquipamentRecord(@NotBlank String name) {
}
