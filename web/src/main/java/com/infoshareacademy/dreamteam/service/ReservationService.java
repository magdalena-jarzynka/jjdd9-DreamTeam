package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.context.UserContextHolder;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Reservation;
import com.infoshareacademy.dreamteam.domain.entity.User;
import com.infoshareacademy.dreamteam.domain.request.ReservationRequest;
import com.infoshareacademy.dreamteam.email.EmailManager;
import com.infoshareacademy.dreamteam.repository.BookRepository;
import com.infoshareacademy.dreamteam.repository.ReservationRepository;
import com.infoshareacademy.dreamteam.repository.UserRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequestScoped
public class ReservationService {

    @EJB
    private ReservationRepository reservationRepository;

    @Inject
    private BookService bookService;

    @Inject
    private BookRepository bookRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private EmailManager emailManager;

    public User findUser(HttpSession httpSession) {
        UserContextHolder userContextHolder = new UserContextHolder(httpSession);
        Long userId = Long.valueOf(userContextHolder.getId());
        return userRepository.findUserById(userId).orElseThrow();
    }

    @Transactional
    public Reservation addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();

        Long userId = reservationRequest.getUserView().getId();
        User user = userRepository.findUserById(userId).orElseThrow();
        reservation.setUser(user);

        Long bookId = reservationRequest.getBookView().getId();
        Book book = bookRepository.findBookById(bookId).orElseThrow();
        reservation.setBook(book);

        reservation.setStartDate(LocalDateTime.now());
        reservation.setEndDate(LocalDateTime.now().plusMinutes(15));
        user.getReservations().add(reservation);
        userRepository.update(user);
        reservationRepository.add(reservation);
        emailManager.sendEmail(reservationRequest);
        return reservation;
    }

    public void delete(Reservation reservation, HttpSession httpSession) {
        User user = findUser(httpSession);
        reservationRepository.delete(reservation);
        user.getReservations().remove(reservation);
    }

}
