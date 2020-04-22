package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationChange {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    private ConfigurationChange() {
    }


    public static void print() {
        STDOUT.info("\n\n To jest metoda umożliwiająca zmianę konfiguracji lub wczytanie jej z pliku. \n\n");
    }
}
