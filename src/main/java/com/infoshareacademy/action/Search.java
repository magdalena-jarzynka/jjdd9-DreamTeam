package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Search {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    private Search() {
    }


    public static void print() {
        STDOUT.info("\n\n To jest metoda umożliwiająca wyszukiwanie książek. \n\n");
    }
}
