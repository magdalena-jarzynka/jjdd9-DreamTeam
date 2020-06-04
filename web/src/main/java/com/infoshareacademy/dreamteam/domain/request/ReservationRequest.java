package com.infoshareacademy.dreamteam.domain.request;

import com.infoshareacademy.dreamteam.domain.view.BookView;
import com.infoshareacademy.dreamteam.domain.view.UserView;

import java.time.LocalDateTime;

public class ReservationRequest {
    private LocalDateTime startDate;
    private UserView userView;
    private BookView bookView;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
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
