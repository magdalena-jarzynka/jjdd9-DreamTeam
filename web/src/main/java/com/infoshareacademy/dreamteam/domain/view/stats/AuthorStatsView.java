package com.infoshareacademy.dreamteam.domain.view.stats;

public class AuthorStatsView {
    private String name;
    private Long reservationCount;

    public AuthorStatsView(String name, Long reservationCount) {
        this.name = name;
        this.reservationCount = reservationCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(Long reservationCount) {
        this.reservationCount = reservationCount;
    }
}
