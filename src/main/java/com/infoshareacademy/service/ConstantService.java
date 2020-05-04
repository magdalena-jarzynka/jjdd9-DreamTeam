package com.infoshareacademy.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;

public class ConstantService {

    public static Properties readProperties(String fileName) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(fileName)) {
            props.load(in);
        } catch (FileNotFoundException e) {
            STDOUT.error("Brak pliku z ustawieniami");
        } catch (IOException e) {
            STDOUT.error("Błąd podczas odczytu ustawień");
        }
        return props;
    }
}
