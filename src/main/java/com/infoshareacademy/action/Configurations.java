package com.infoshareacademy.action;

import java.io.*;
import java.util.Properties;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;

public class Configurations {
    private static Properties appProperties;

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

    public static void writeToProperties(String key, String value) {
        appProperties.setProperty(key, value);
        try (OutputStream output = new FileOutputStream("config.properties")) {
            appProperties.store(output, null);
        } catch (IOException io) {
            STDOUT.error("Brak dostępu do pliku z konfiguracją! \n");
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
