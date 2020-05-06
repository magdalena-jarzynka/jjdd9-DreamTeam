package com.infoshareacademy.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuUtils {
    public static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    public static final String WRONG_NUMBER = "Proszę wpisać odpowiednią cyfrę. \n";

    private MenuUtils() {
    }

    public static void cleanTerminal() {
        STDOUT.info("\033\143");
    }
}