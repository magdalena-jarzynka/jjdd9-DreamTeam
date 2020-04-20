package com.infoshareacademy.menu.item;

public enum ReservationMenu {
    NEW_RESERVATION("NOWA REZERWACJA"),
    CANCEL_RESERVATION("ANULUJ REZERWACJĘ");

    String reservationValue;

    ReservationMenu(String reservationValue){
        this.reservationValue = reservationValue;
    }

    public String getReservationValue(){
        return reservationValue;
    }
}
