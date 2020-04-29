package com.infoshareacademy.action.search;

import com.infoshareacademy.input.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class CriteriaChoice {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean[] usedCriteria = new boolean[3];

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

    protected static String title() {
        while (true) {
            STDOUT.info("Proszę podać tytuł: \n");
            String lineInput = scanner.nextLine();
            if (lineInput.length() > 2) {
                usedCriteria[0] = true;
                return lineInput;
            } else {
                STDOUT.info("Zapytanie musi zawierać co najmniej dwa znaki. \n");
            }
        }
    }

    protected static String author() {
        while (true) {
            STDOUT.info("Proszę podać imię autora: \n");
            String lineInput = scanner.nextLine();
            if (lineInput.length() > 2) {
                usedCriteria[1] = true;
                return lineInput;
            } else {
                STDOUT.info("Zapytanie musi zawierać co najmniej dwa znaki. \n");
            }
        }
    }

    protected static boolean audio() {
        while (true) {
            STDOUT.info("Proszę wybrać 1 aby wyszukać tytuły z wersją audio lub 2 dla tytułów bez wersji audio \n");
            switch (UserInput.getUserInput()) {
                case 1: {
                    usedCriteria[2] = true;
                    return true;
                }
                case 2: {
                    usedCriteria[2] = true;
                    return false;
                }
                default:
                    STDOUT.info("Proszę wpisać odpowiednią cyfrę.");
            }
        }
    }
}
