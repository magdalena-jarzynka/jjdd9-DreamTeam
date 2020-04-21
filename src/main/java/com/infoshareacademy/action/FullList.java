package com.infoshareacademy.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FullList {
    private FullList() {
    }

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");


    public static void print() {
        STDOUT.info("\n\n W tym miejscu wyświetla się pełna lista książek. \n\n");
    }
}
