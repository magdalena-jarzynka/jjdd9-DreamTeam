package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortingOptions {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public SortingOptions() {
    }

    public void print() {
        STDOUT.info("\n\n Ta metoda umożliwia ustawienie preferencji sortowania wyników (rosnąco lub malejąco). \n\n");
    }
}
