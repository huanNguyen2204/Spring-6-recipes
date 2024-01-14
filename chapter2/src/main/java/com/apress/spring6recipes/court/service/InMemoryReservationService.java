package com.apress.spring6recipes.court.service;

import com.apress.spring6recipes.court.domain.Player;
import com.apress.spring6recipes.court.domain.Reservation;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class InMemoryReservationService implements ReservationService {

    private final List<Reservation> reservations =
        Collections.synchronizedList(new ArrayList<>());

    public InMemoryReservationService() {
        var roger = new Player("Roger");
        var james = new Player("James");
        var date = LocalDate.of(2022, 10, 18);

        reservations.add(new Reservation("Tennis #1", date, 16, roger, TENNIS));
        reservations.add(new Reservation("Tennis #2", date, 20, james, TENNIS));
    }

    @Override
    public List<Reservation> query(String courtName) {
        return this.reservations
            .stream()
            .filter((r) -> StringUtils.startsWithIgnoreCase(r.getCourtName(), courtName))
            .collect(Collectors.toList());
    }
}
