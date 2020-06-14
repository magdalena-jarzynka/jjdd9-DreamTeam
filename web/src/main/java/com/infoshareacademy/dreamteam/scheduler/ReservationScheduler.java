package com.infoshareacademy.dreamteam.scheduler;

import com.infoshareacademy.dreamteam.service.ReservationService;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class ReservationScheduler {

    @Inject
    ReservationService reservationService;

    @Schedule(hour = "*", minute = "*/3", second = "*")
    public void cancelUnconfirmedReservations() {
        reservationService.cancelUnconfirmedReservations();
    }

}
