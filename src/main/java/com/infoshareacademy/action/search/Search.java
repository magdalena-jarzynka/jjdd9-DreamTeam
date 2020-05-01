package com.infoshareacademy.action.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Search {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    CriteriaChoice userCriteria = new CriteriaChoice();

    public void run() {
        do {
            showSearchPanel();
            switch (userCriteria.getUserChoice()) {
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


}
