package com.infoshareacademy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConstantService {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    public static final String NO_FILE = "Brak pliku";
    public static final String ERROR = "Błąd podczas odczytu pliku";

    public static Properties readProperties(String fileName) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(fileName)) {
            props.load(in);
        } catch (FileNotFoundException e) {
            STDOUT.error(NO_FILE);
        } catch (IOException e) {
            STDOUT.error(ERROR);
        }
        return props;
    }
}
