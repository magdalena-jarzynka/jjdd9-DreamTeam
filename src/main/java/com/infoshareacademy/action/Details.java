package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Details {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public Details() {
    }


    public void print() {
        STDOUT.info("\n\n Ta metoda pozwala na wyświetlenie szczegółowych informacji o książce. \n\n");
    }
}
