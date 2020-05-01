package com.infoshareacademy.action.search;

import com.infoshareacademy.input.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class CriteriaChoice {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final Scanner scanner = new Scanner(System.in);
    private boolean[] activeCriteria = new boolean[3];
    private String author = "";
    private String title = "";
    private boolean audio = false;

    public boolean[] getActiveCriteria() {
        return activeCriteria;
    }

    public int getUserChoice() {
        STDOUT.info("Proszę wybrać kryterium wyszukiwania lub rozpocząć wyszukiwanie: \n");
        STDOUT.info("1. Tytuł \n");
        STDOUT.info("2. Imię autora \n");
        STDOUT.info("3. Dostępność wersji audio \n");
        STDOUT.info("4. Rozpoczęcie wyszukiwania \n");
        STDOUT.info("Wybierz 0 aby opuścić wyszukiwarkę książek \n");
        return UserInput.getUserInput();
    }

    public void setTitle() {
        while (true) {
            STDOUT.info("Proszę podać tytuł: \n");
            String lineInput = scanner.nextLine();
            if (lineInput.length() > 2) {
                activeCriteria[0] = true;
                title = lineInput.toLowerCase();
                return;
            } else {
                STDOUT.info("Zapytanie musi zawierać co najmniej dwa znaki. \n");
            }
        }
    }

    public void setAuthor() {
        while (true) {
            STDOUT.info("Proszę podać imię autora: \n");
            String lineInput = scanner.nextLine();
            if (lineInput.length() > 2) {
                activeCriteria[1] = true;
                author = lineInput.toLowerCase();
                return;
            } else {
                STDOUT.info("Zapytanie musi zawierać co najmniej dwa znaki. \n");
            }
        }
    }

    public void setAudio() {
        while (true) {
            STDOUT.info("Proszę wybrać 1 aby wyszukać tytuły z wersją audio lub 2 dla tytułów bez wersji audio \n");
            switch (UserInput.getUserInput()) {
                case 1: {
                    activeCriteria[2] = true;
                    audio = true;
                    return;
                }
                case 2: {
                    activeCriteria[2] = true;
                    audio = false;
                    return;
                }
                default:
                    STDOUT.info("Proszę wpisać odpowiednią cyfrę.");
            }
        }
    }

    public String getAuthor() { return author; }

    public String getTitle() { return title; }

    public boolean getAudio()    { return audio; }
}
