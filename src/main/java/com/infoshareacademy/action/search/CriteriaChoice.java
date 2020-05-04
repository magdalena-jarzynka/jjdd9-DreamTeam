package com.infoshareacademy.action.search;

import com.infoshareacademy.input.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class CriteriaChoice {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final Scanner scanner = new Scanner(System.in);
    private final boolean[] activeCriteria = new boolean[3];
    private String author = "";
    private String title = "";
    private boolean audio = false;

    public boolean[] getActiveCriteria() {
        return activeCriteria;
    }

    public void setTitle() {
        activeCriteria[0] = true;
        STDOUT.info("Proszę podać tytuł: \n");
        while (true) {
            String lineInput = scanner.nextLine();
            if (lineInput.length() > 2) {

                title = lineInput.toLowerCase();
                return;
            } else {
                STDOUT.info("Zapytanie musi zawierać co najmniej trzy znaki. \n");
            }
        }
    }

    public void setAuthor() {
        activeCriteria[1] = true;
        STDOUT.info("Proszę podać imię autora: \n");
        while (true) {
            String lineInput = scanner.nextLine();
            if (lineInput.length() > 2) {
                author = lineInput.toLowerCase();
                return;
            } else {
                STDOUT.info("Zapytanie musi zawierać co najmniej trzy znaki. \n");
            }
        }
    }

    public void setAudio() {
        activeCriteria[2] = true;
        STDOUT.info("Proszę wybrać 1 aby wyszukać tytuły z wersją audio lub 2 dla tytułów bez wersji audio \n");
        while (true) {
            switch (UserInput.getUserInput()) {
                case 1: {
                    audio = true;
                    return;
                }
                case 2: {
                    audio = false;
                    return;
                }
                default:
                    STDOUT.info("Proszę wpisać odpowiednią cyfrę.");
            }
        }
    }
    public void resetCriteria() {
        for(int i = 0; i < 3; i++) {
            activeCriteria[i] = false;
        }
    }

    public String getAuthor() { return author; }

    public String getTitle() { return title; }

    public boolean getAudio()    { return audio; }
}
