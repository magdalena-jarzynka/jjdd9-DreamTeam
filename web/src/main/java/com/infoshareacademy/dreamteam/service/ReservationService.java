package com.infoshareacademy.dreamteam.service;


import com.infoshareacademy.dreamteam.domain.entity.Reservation;
import com.infoshareacademy.dreamteam.domain.view.ReservationView;
import com.infoshareacademy.dreamteam.repository.ReservationRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ReservationService {

    @EJB
    private ReservationRepository reservationRepository;

    @Inject
    private BookService bookService;


    public void addReservation(ReservationView reservationView) {

        Long bookViewId = reservationView.getBookView().getId();

        Reservation reservation = new Reservation();
        reservation.setBook(bookRepository.);

        reservationRepository.add(reservation);
    }

    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }


}
