package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataFormat {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public DataFormat() {
    }


    public void print() {
        STDOUT.info("\n\n Ta metoda pozwala na zmianę formatu wyświetlanej daty. \n\n");
    }
}
