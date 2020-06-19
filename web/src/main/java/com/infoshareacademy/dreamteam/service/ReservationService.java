package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Reservation;
import com.infoshareacademy.dreamteam.domain.entity.User;
import com.infoshareacademy.dreamteam.domain.view.ReservationView;
import com.infoshareacademy.dreamteam.domain.view.UserView;
import com.infoshareacademy.dreamteam.email.BookReservationEmailBuilder;
import com.infoshareacademy.dreamteam.email.EmailManager;
import com.infoshareacademy.dreamteam.email.OutdatedReservationEmailBuilder;
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
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequestScoped
public class ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class.getName());
    public static final String CONFIRMATION_FAILURE = "Bardzo nam przykro, ale rezerwacja książki wygasła. \n " +
            "Pamiętaj, że na potwierdzenie masz dokładnie 15 minut.";
    public static final String CONFIRMATION_SUCCESS = "Twoja rezerwacja została poprawnie potwierdzona. \n" +
            "Twoja rezerwacja już powinna być widoczna w panelu REZERWACJE.";

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
    public Reservation addReservation(Long userId, Long bookId) {
        Reservation reservation = new Reservation();

        Book book = bookRepository.findBookById(bookId).get();
        reservation.setBook(book);
        reservation.setConfirmed(false);
        reservation.setToken(String.valueOf(UUID.randomUUID()));

        User user = userRepository.findUserById(userId).orElseThrow();
        reservation.setUser(user);

        reservation.setStartDate(LocalDateTime.now());
        reservation.setEndDate(LocalDateTime.now().plusMinutes(getReservationProperty("confirm.reservation.time")));
        user.getReservations().add(reservation);
        userRepository.update(user);
        reservationRepository.add(reservation);
        String confirmationUrl = emailManager.getEmailProperty("url") + reservation.getToken();
        emailManager.sendEmail(new BookReservationEmailBuilder(book.getTitle(), confirmationUrl), user.getEmail());
        return reservation;
    }

    @Transactional
    public Boolean confirmReservation(ReservationView reservationView) {
        Reservation reservation = findReservationById(reservationView.getId());
        boolean expired = reservation.getEndDate().isBefore(LocalDateTime.now());
        if (!expired) {
            reservation.setConfirmed(true);
            reservationRepository.update(reservation);
            Long bookId = reservationView.getBookView().getId();
            Long userId = reservationView.getUserView().getId();

            return true;
        } else {
            reservation.getBook().getReservations().remove(reservation);
            return false;
        }
    }

    private Reservation findReservationById(Long id) {
        return reservationRepository.findReservationById(id).orElseThrow();
    }

    @Transactional
    public Optional<ReservationView> findReservationViewByToken(String token) {
        Optional<Reservation> reservationOpt = reservationRepository.findReservationByToken(token);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            ReservationView reservationView = reservationMapper.mapEntityToView(reservation);
            reservationView.setBookView(bookMapper.mapEntityToView(reservation.getBook()));
            reservationView.setUserView(userMapper.mapEntityToView(reservation.getUser()));
            return Optional.of(reservationView);
        }
        return Optional.empty();
    }

    @Transactional
    private List<ReservationView> findReservationsByUser(UserView userView) {
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

    @Transactional
    public List<ReservationView> findConfirmedReservationsByUser(Long userId) {
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

    public void deleteReservation(Reservation reservation) {
        reservationRepository.delete(reservation.getId());
    }

    public void cancelUnconfirmedReservations() {
        List<Reservation> reservations = reservationRepository.findAllReservations();
        if (!reservations.isEmpty()) {
            reservations.stream()
                    .filter(reservation -> reservation.getEndDate().isBefore(LocalDateTime.now()))
                    .filter(reservation -> !reservation.getConfirmed())
                    .forEach(this::deleteReservation);
        }
    }

    public String getConfirmationResult(ReservationView reservationView) {
        if (confirmReservation(reservationView)) {
            return CONFIRMATION_SUCCESS;
        }
        return CONFIRMATION_FAILURE;
    }

    public void cancelOutdatedReservations() {
        List<Reservation> reservations = reservationRepository.findAllReservations();
        if (!reservations.isEmpty()) {
            reservations.stream()
                    .filter(reservation -> LocalDateTime.now()
                            .isAfter(reservation.getEndDate()
                                    .plusMinutes(getReservationProperty("outdated.reservation.time"))))
                    .forEach(reservation -> {
                        emailManager.sendEmail(new OutdatedReservationEmailBuilder(reservation.getBook().getTitle()),
                                reservation.getUser().getEmail());
                        deleteReservation(reservation);
                    });
        }
    }

    private Long getReservationProperty(String property) {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Thread.currentThread()
                    .getContextClassLoader().getResource("reservation.properties"))
                    .openStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return Long.valueOf(properties.getProperty(property));
    }

}
