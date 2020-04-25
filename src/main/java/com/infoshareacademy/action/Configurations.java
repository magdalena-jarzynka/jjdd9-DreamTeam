package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configurations {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public Configurations() {
    }


    public void print() {
        STDOUT.info("\n\n To jest metoda umożliwiająca zmianę konfiguracji lub wczytanie jej z pliku. \n\n");
    }
}
