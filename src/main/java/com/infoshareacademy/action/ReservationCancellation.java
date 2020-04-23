package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReservationCancellation {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public ReservationCancellation() {
    }


    public void print() {
        STDOUT.info("\n\n Ta metoda pozwala na usunięcie istniejącej rezerwacji książki. \n\n");
    }
}
