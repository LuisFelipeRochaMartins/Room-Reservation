package com.github.luisfeliperochamartins.roomReservation.domain.reservation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationRecord(@NotNull Long roomId,
                                @NotNull List<Long> userIds,
                                @Future LocalDateTime startAt,
                                @Future LocalDateTime endAt) {
}
