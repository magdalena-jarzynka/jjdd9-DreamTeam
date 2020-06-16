package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Reservation;
import com.infoshareacademy.dreamteam.domain.entity.User;
import com.infoshareacademy.dreamteam.domain.request.ReservationRequest;
import com.infoshareacademy.dreamteam.domain.view.ReservationView;
import com.infoshareacademy.dreamteam.domain.view.UserView;
import com.infoshareacademy.dreamteam.email.BookReservationEmailBuilder;
import com.infoshareacademy.dreamteam.email.EmailManager;
import com.infoshareacademy.dreamteam.mapper.BookMapper;
import com.infoshareacademy.dreamteam.mapper.ReservationMapper;
import com.infoshareacademy.dreamteam.mapper.UserMapper;
import com.infoshareacademy.dreamteam.repository.BookRepository;
import com.infoshareacademy.dreamteam.repository.ReservationRepository;
import com.infoshareacademy.dreamteam.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestScoped
public class ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class.getName());
    public static final String CONFIRMATION_FAILURE = "Bardzo nam przykro, ale rezerwacja książki wygasła. \n" +
            "Pamiętaj, że na potwierdzenie masz dokładnie 15 minut.";
    public static final String CONFIRMATION_SUCCESS = "Twoja rezerwacja została poprawnie potwierdzona. \n" +
            "Twoja rezerwacja już powinna być widoczna w panelu REZERWACJE.";
//TODO STRING FORMAT!

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

    @Inject
    private UserService userService;

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
        reservation.setEndDate(LocalDateTime.now().plusMinutes(15));
        user.getReservations().add(reservation);
        userRepository.update(user);
        reservationRepository.add(reservation);
        String confirmationUrl = emailManager.getEmailProperty("url") + reservationRequest.getToken();
        emailManager.sendEmail(new BookReservationEmailBuilder(book.getTitle(), confirmationUrl), reservationRequest.getUserView().getEmail());
        return reservation;
    }

    @Transactional
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
    public Optional<ReservationView> findReservationViewByToken(String token) {
        Reservation reservation = reservationRepository.findReservationByToken(token).get();
        ReservationView reservationView = reservationMapper.mapEntityToView(reservation);
        reservationView.setBookView(bookMapper.mapEntityToView(reservation.getBook()));
        reservationView.setUserView(userMapper.mapEntityToView(reservation.getUser()));
        return Optional.of(reservationView);
    }

    @Transactional
    public List<ReservationView> findReservationsByUser(UserView userView) {
        User user = userService.findUserById(userView.getId());
        List<Reservation> reservations = user.getReservations();
        List<ReservationView> reservationViews = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationView reservationView = reservationMapper.mapEntityToView(reservation);
            reservationView.setBookView(bookMapper.mapEntityToView(reservation.getBook()));
            reservationView.setUserView(userMapper.mapEntityToView(reservation.getUser()));
            reservationViews.add(reservationView);
        }
        return reservationViews;
    }

    private List<ReservationView> findConfirmedReservationsByUser(Long userId) {
        return findReservationsByUser(userService.findUserViewById(userId))
                .stream()
                .filter(ReservationView::getConfirmed)
                .collect(Collectors.toList());
    }

    public ReservationView findReservationByUserIdAndBookId(Long userId, Long bookId) {
        Reservation reservation = reservationRepository.findReservationRequestByUserIdAndBookId(userId, bookId).get();
        ReservationView reservationView = reservationMapper.mapEntityToView(reservation);
        reservationView.setBookView(bookMapper.mapEntityToView(reservation.getBook()));
        reservationView.setUserView(userMapper.mapEntityToView(reservation.getUser()));
        return reservationView;
    }

    @Transactional
    public void delete(Long userId, Long bookId) {
        ReservationView reservationView = findReservationByUserIdAndBookId(userId, bookId);
        reservationRepository.delete(reservationView.getId());
    }

    public void removeUnconfirmedReservation(Reservation reservation) {
        reservationRepository.delete(reservation.getId());
    }

    public void cancelUnconfirmedReservations() {
        List<Reservation> reservations = reservationRepository.findAllReservations();
        if (!reservations.isEmpty()) {
            reservations.stream()
                    .filter(reservation -> reservation.getEndDate().isBefore(LocalDateTime.now()))
                    .filter(reservation -> !reservation.getConfirmed())
                    .forEach(this::removeUnconfirmedReservation);
        }
    }

    public String getConfirmationResult(ReservationView reservationView) {
        if (confirmReservation(reservationView)) {
            return CONFIRMATION_SUCCESS;
        }
        return CONFIRMATION_FAILURE;
    }



}
