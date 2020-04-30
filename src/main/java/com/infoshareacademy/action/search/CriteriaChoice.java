package com.infoshareacademy.action.search;

import com.infoshareacademy.input.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class CriteriaChoice {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean[] usedCriteria = new boolean[3];
    private static String author = "";
    private static String title = "";
    private static boolean audio = false;

    public static boolean[] getUsedCriteria() {
        return usedCriteria;
    }

    protected static int userChoice() {
        STDOUT.info("Proszę wybrać kryterium wyszukiwania lub rozpocząć wyszukiwanie: \n");
        STDOUT.info("1. Tytuł \n");
        STDOUT.info("2. Imię autora \n");
        STDOUT.info("3. Dostępność wersji audio \n");
        STDOUT.info("4. Rozpoczęcie wyszukiwania \n");
        STDOUT.info("5. Wyjście z wyszukiwarki książek \n");
        return UserInput.getUserInput();
    }

    protected static void setTitle() {
        while (true) {
            STDOUT.info("Proszę podać tytuł: \n");
            String lineInput = scanner.nextLine();
            if (lineInput.length() > 2) {
                usedCriteria[0] = true;
                title = lineInput.toLowerCase();
                return;
            } else {
                STDOUT.info("Zapytanie musi zawierać co najmniej dwa znaki. \n");
            }
        }
    }

    protected static void setAuthor() {
        while (true) {
            STDOUT.info("Proszę podać imię autora: \n");
            String lineInput = scanner.nextLine();
            if (lineInput.length() > 2) {
                usedCriteria[1] = true;
                author = lineInput.toLowerCase();
                return;
            } else {
                STDOUT.info("Zapytanie musi zawierać co najmniej dwa znaki. \n");
            }
        }
    }

    protected static void setAudio() {
        while (true) {
            STDOUT.info("Proszę wybrać 1 aby wyszukać tytuły z wersją audio lub 2 dla tytułów bez wersji audio \n");
            switch (UserInput.getUserInput()) {
                case 1: {
                    usedCriteria[2] = true;
                    audio = true;
                    return;
                }
                case 2: {
                    usedCriteria[2] = true;
                    audio = false;
                    return;
                }
                default:
                    STDOUT.info("Proszę wpisać odpowiednią cyfrę.");
            }
        }
    }

    public static String getAuthor() { return author; }

    public static String getTitle() { return title; }

    public static boolean getAudio()    { return audio; }
}
