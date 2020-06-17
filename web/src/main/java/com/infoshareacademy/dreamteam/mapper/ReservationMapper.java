package com.infoshareacademy.dreamteam.mapper;

import com.infoshareacademy.dreamteam.domain.entity.Reservation;
import com.infoshareacademy.dreamteam.domain.view.ReservationView;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ReservationMapper {

    public ReservationView mapEntityToView(Reservation reservation) {
        ReservationView reservationView = new ReservationView();
        reservationView.setId(reservation.getId());
        reservationView.setStartDate(reservation.getStartDate());
        reservationView.setEndDate(reservation.getEndDate());
        reservationView.setToken(reservation.getToken());
        reservationView.setConfirmed(reservation.getConfirmed());
        return reservationView;
    }

}
