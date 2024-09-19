package com.github.luisfeliperochamartins.roomReservation.domain.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RoomRecord(@NotBlank String name,
                         @NotNull Integer capacity,
                         List<Long> equipamentsIds) {
}
