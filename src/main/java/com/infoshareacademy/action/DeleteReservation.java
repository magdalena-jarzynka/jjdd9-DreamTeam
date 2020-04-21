package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteReservation {

    private DeleteReservation() {
    }

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public static void print() {
        STDOUT.info("\n\n Ta metoda pozwala na usunięcie istniejącej rezerwacji książki. \n\n");
    }
}
