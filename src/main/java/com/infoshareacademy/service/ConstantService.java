package com.infoshareacademy.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;

public class ConstantService {
    public static final String NO_FILE = "Brak pliku";
    public static final String ERROR = "Błąd podczas odczytu pliku";

    private ConstantService() {
        // Utility class
    }

    public static Properties readProperties(String fileName) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(fileName)) {
            props.load(in);
        } catch (FileNotFoundException e) {
            STDOUT.info(NO_FILE);
        } catch (IOException e) {
            STDOUT.info(ERROR);
        }
        return props;
    }
}
