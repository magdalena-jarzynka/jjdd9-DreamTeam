package com.infoshareacademy.dreamteam.domain.request;

import com.infoshareacademy.dreamteam.domain.view.BookView;
import com.infoshareacademy.dreamteam.domain.view.UserView;

import java.time.LocalDateTime;

public class ReservationRequest {
    private String token;
    private Boolean confirmed;
    private LocalDateTime startTime;
    private LocalDateTime expiryTime;
    private UserView userView;
    private BookView bookView;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
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

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
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
