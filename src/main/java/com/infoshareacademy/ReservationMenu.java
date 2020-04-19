package com.infoshareacademy;

public enum ReservationMenu {
    NEW_RESERVATION("NOWA_REZERWACJA"),
    CANCEL_RESERVATION("ANULUJ_REZERWACJĘ");

    String reservationValue;

    ReservationMenu(String rv){
        reservationValue =rv;
    }

    public String getReservationValue(){
        return reservationValue;
    }
}
