package com.apress.spring6recipes.court.service;

import com.apress.spring6recipes.court.domain.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> query(String courtName);
}
