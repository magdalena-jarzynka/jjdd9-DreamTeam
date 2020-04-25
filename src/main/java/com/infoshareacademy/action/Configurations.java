package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class Configurations {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static Properties appProperties;

    public Configurations() {
    }

    public static Properties readProperties(String fileName) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(fileName)) {
            props.load(in);
        } catch (FileNotFoundException e) {
            STDOUT.error("Brak pliku z ustawieniami", e);
        } catch (IOException e) {
            STDOUT.error("Błąd podczas odczytu ustawień", e);
        }
        return props;
    }

    public static void writeToProperties(String key, String value) {
        appProperties.setProperty(key, value);
        try (OutputStream output = new FileOutputStream("config.properties")) {
            appProperties.store(output, null);
        } catch (IOException io) {
            STDOUT.error("Brak dostępu do pliku z konfiguracją! \n", io);
        }
    }

    public static Properties getProperties() {
        return appProperties;
    }

    public static void setDefaultProperties() {
        appProperties = readProperties("src/main/resources/default.properties");
    }

    public void print() {
        STDOUT.info("\n\n To jest metoda umożliwiająca zmianę konfiguracji lub wczytanie jej z pliku. \n\n");
    }
}
