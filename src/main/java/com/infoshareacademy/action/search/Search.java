package com.infoshareacademy.action.search;

import com.infoshareacademy.input.UserInputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Search {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private UserInputService userInputService = new UserInputService();

    CriteriaChoice userCriteria = new CriteriaChoice();

    public void run() {
        do {
            showSearchPanel();
            switch (getUserChoice()) {
                case 1: {
                    userCriteria.setTitle();
                    break;
                }
                case 2: {
                    userCriteria.setAuthor();
                    break;
                }
                case 3: {
                    userCriteria.setAudio();
                    break;
                }
                case 4: {
                    Filtration.run(userCriteria);
                    break;
                }
                case 5: {
                    userCriteria.resetCriteria();
                    break;
                }
                case 0: {
                    return;
                }
            }
        } while (true);
    }

    private void showSearchPanel() {
        STDOUT.info("\033\143");
        STDOUT.info("WYSZUKIWANIE KSIĄŻEK \n\n");
        if(userCriteria.getActiveCriteria()[0]) {
            STDOUT.info("Tytuł: {} \n", userCriteria.getTitle());
        }
        if(userCriteria.getActiveCriteria()[1]) {
            STDOUT.info("Autor: {} \n", userCriteria.getAuthor());
        }
        if (userCriteria.getActiveCriteria()[2]) {
            STDOUT.info("Wersja audio: {} \n\n", userCriteria.getAudio() ? "tak" : "nie");
        }
    }

    public int getUserChoice() {
        STDOUT.info("Proszę wybrać kryterium wyszukiwania lub rozpocząć wyszukiwanie: \n");
        STDOUT.info("1. Tytuł \n");
        STDOUT.info("2. Imię autora \n");
        STDOUT.info("3. Dostępność wersji audio \n");
        STDOUT.info("4. Rozpoczęcie wyszukiwania \n");
        STDOUT.info("5. Reset kryteriów wyszukiwania \n");
        STDOUT.info("Wybierz 0 aby opuścić wyszukiwarkę książek \n");
        return userInputService.getUserInput();
    }
}
