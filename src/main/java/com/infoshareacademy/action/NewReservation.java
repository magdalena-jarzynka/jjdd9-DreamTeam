package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewReservation {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public NewReservation() {
    }


    public void print() {

        STDOUT.info("\n\n To jest metoda dodająca nową rezerwację. \n\n");
    }
}
