package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.context.UserContextHolder;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Reservation;
import com.infoshareacademy.dreamteam.domain.entity.User;
import com.infoshareacademy.dreamteam.domain.request.ReservationRequest;
import com.infoshareacademy.dreamteam.domain.view.ReservationView;
import com.infoshareacademy.dreamteam.email.EmailManager;
import com.infoshareacademy.dreamteam.mapper.BookMapper;
import com.infoshareacademy.dreamteam.mapper.ReservationMapper;
import com.infoshareacademy.dreamteam.mapper.UserMapper;
import com.infoshareacademy.dreamteam.repository.BookRepository;
import com.infoshareacademy.dreamteam.repository.ReservationRepository;
import com.infoshareacademy.dreamteam.repository.UserRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class ReservationService {

    @EJB
    private ReservationRepository reservationRepository;

    @Inject
    private BookRepository bookRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private EmailManager emailManager;

    @Inject
    private ReservationMapper reservationMapper;

    @Inject
    private BookMapper bookMapper;

    @Inject
    private UserMapper userMapper;

    public User findUser(HttpSession httpSession) {
        UserContextHolder userContextHolder = new UserContextHolder(httpSession);
        Long userId = Long.valueOf(userContextHolder.getId());
        return userRepository.findUserById(userId).orElseThrow();
    }

    @Transactional
    public Reservation addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();

        Long bookId = reservationRequest.getBookView().getId();
        Book book = bookRepository.findBookById(bookId).orElseThrow();

        reservation.setBook(book);
        reservation.setConfirmed(reservationRequest.getConfirmed());
        reservation.setToken(reservationRequest.getToken());

        Long userId = reservationRequest.getUserView().getId();
        User user = userRepository.findUserById(userId).orElseThrow();
        reservation.setUser(user);

        reservation.setStartDate(LocalDateTime.now());
        //TODO na czas testów ustawiona 1 minuta
//        reservation.setEndDate(LocalDateTime.now().plusMinutes(15));
        reservation.setEndDate(LocalDateTime.now().plusMinutes(1));
        user.getReservations().add(reservation);
        userRepository.update(user);
        reservationRepository.add(reservation);
        emailManager.sendEmail(reservationRequest);
        return reservation;
    }

    public Boolean confirmReservation(ReservationView reservationView) {
        Reservation reservation = findReservationById(reservationView.getId());
        boolean notExpired = reservation.getEndDate().isAfter(LocalDateTime.now());
        if (notExpired) {
            reservation.setConfirmed(true);
            reservationRepository.update(reservation);
            return true;
        } else {
            reservation.getBook().getReservations().remove(reservation);
            return false;
        }
    }

    public Reservation findReservationById(Long id) {
        return reservationRepository.findReservationById(id).orElseThrow();
    }

    @Transactional
    public ReservationView findReservationViewByToken(String token) {
        Reservation reservation = reservationRepository.findReservationByToken(token).orElseThrow();
        ReservationView reservationView = reservationMapper.mapEntityToView(reservation);
        reservationView.setBookView(bookMapper.mapEntityToView(reservation.getBook()));
        reservationView.setUserView(userMapper.mapEntityToView(reservation.getUser()));
        return reservationView;
    }

    public List<ReservationView> findReservationsByUser(User user) {
        List<Reservation> reservations = user.getReservations();
        List<ReservationView> reservationViews = new ArrayList<>();
        for(Reservation reservation : reservations){
            ReservationView reservationView = reservationMapper.mapEntityToView(reservation);
            reservationView.setBookView(bookMapper.mapEntityToView(reservation.getBook()));
            reservationView.setUserView(userMapper.mapEntityToView(reservation.getUser()));
            reservationViews.add(reservationView);
        }
        return reservationViews;
    }

    public void delete(Reservation reservation, HttpSession httpSession) {
        User user = findUser(httpSession);
        reservationRepository.delete(reservation);
        user.getReservations().remove(reservation);
    }

}
