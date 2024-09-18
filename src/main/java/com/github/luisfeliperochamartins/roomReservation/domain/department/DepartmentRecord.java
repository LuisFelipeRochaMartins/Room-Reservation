package com.github.luisfeliperochamartins.roomReservation.domain.department;

import jakarta.validation.constraints.NotBlank;

public record DepartmentRecord(@NotBlank String name) {
}
