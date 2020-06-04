package com.infoshareacademy.dreamteam.service;


import com.infoshareacademy.dreamteam.dao.BookDao;
import com.infoshareacademy.dreamteam.dao.UserDao;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Reservation;
import com.infoshareacademy.dreamteam.domain.entity.User;
import com.infoshareacademy.dreamteam.domain.view.ReservationView;
import com.infoshareacademy.dreamteam.repository.ReservationRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@RequestScoped
public class ReservationService {

    @EJB
    private ReservationRepository reservationRepository;

    @Inject
    private BookService bookService;

    @Inject
    private BookDao bookDao;

    @Inject
    private UserDao userDao;

    public void addReservation(ReservationView reservationView) {
        Long bookViewId = reservationView.getBookView().getId();
        Long userViewId = reservationView.getUserView().getId();
        User user = userDao.findUserById(userViewId).orElseThrow();
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDateTime.now());
        reservation.setBook(bookDao.findBookById(bookViewId)
                .orElse(new Book("Nie znaleziono książki o podanym identyfikatorze.")));
        reservation.setUser(userDao.findUserById(user.getId()).orElseThrow());
        reservationRepository.add(reservation);
    }

    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    public ReservationView findReservationById(Long id){
        Reservation reservation = reservationRepository.
    }


}
