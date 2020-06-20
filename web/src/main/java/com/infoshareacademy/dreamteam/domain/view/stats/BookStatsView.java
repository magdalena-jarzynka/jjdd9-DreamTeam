package com.infoshareacademy.dreamteam.domain.view.stats;

public class BookStatsView {
    private String title;
    private Long ReservationCount;

    public BookStatsView(String title, Long reservationCount) {
        this.title = title;
        ReservationCount = reservationCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getReservationCount() {
        return ReservationCount;
    }

    public void setReservationCount(Long reservationCount) {
        ReservationCount = reservationCount;
    }
}
