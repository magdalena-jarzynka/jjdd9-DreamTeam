package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Details {
    private static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public static void print() {
        STDOUT.info("\n\n Ta metoda pozwala na wyświetlenie szczegółowych informacji o książce. \n\n");
    }
}