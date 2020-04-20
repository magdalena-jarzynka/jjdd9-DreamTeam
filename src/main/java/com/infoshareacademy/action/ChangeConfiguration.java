package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangeConfiguration {

    private static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public static void print() {
        STDOUT.info("\n\n To jest metoda umożliwiająca zmianę konfiguracji lub wczytanie jej z pliku. \n\n");
    }
}
