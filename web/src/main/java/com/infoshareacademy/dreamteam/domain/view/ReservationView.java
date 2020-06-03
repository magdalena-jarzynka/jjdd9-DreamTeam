package com.infoshareacademy.dreamteam.domain.view;

import java.time.LocalDateTime;

public class ReservationView {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private UserView userView;
    private BookView bookView;

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

    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public BookView getBookView() {
        return bookView;
    }

    public void setBookView(BookView bookView) {
        this.bookView = bookView;
    }
}
