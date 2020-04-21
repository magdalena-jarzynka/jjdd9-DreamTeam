package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReservationAddition {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    private ReservationAddition() {
    }


    public static void print() {

        STDOUT.info("\n\n To jest metoda dodająca nową rezerwację. \n\n");
    }
}
