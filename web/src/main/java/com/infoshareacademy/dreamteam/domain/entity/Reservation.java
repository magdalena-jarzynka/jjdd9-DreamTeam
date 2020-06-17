package com.infoshareacademy.dreamteam.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(
                name = "Reservation.findReservationsByUser",
                query = "SELECT r FROM Reservation r WHERE r.user =: user"
        ),
        @NamedQuery(
                name = "Reservation.findReservationByBook",
                query = "SELECT r FROM Reservation r WHERE r.book =: book"
        ),
        @NamedQuery(
                name = "Reservation.findReservationByToken",
                query = "SELECT r FROM Reservation r WHERE r.token =: token"
        ),
        @NamedQuery(
                name = "Reservation.findReservationById",
                query = "SELECT r FROM Reservation r WHERE r.id =: id"
        ),
        @NamedQuery(
                name = "Reservation.findReservationRequestByUserIdAndBookId",
                query = "SELECT r FROM Reservation r WHERE r.user.id =: user_id AND r.book.id =: book_id"
        ),
        @NamedQuery(
                name = "Reservation.findAllReservations",
                query = "SELECT r FROM Reservation r"
        )
})

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Boolean confirmed;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }
}
