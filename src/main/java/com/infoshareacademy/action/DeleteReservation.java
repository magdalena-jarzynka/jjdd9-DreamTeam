package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteReservation {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    private DeleteReservation() {
    }


    public static void print() {
        STDOUT.info("\n\n Ta metoda pozwala na usunięcie istniejącej rezerwacji książki. \n\n");
    }
}
