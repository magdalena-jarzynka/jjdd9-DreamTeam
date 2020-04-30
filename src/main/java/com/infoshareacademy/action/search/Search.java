package com.infoshareacademy.action.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Search {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");


    public void run() {
        do {
            showSearchPanel();
            switch (CriteriaChoice.userChoice()) {
                case 1: {
                    CriteriaChoice.setTitle();
                    break;
                }
                case 2: {
                    CriteriaChoice.setAuthor();
                    break;
                }
                case 3: {
                    CriteriaChoice.setAudio();
                    break;
                }
                case 4: {
                    Filtration.run();
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
        if(CriteriaChoice.getUsedCriteria()[0]) {
            STDOUT.info("Tytuł: {} \n", CriteriaChoice.getTitle());
        }
        if(CriteriaChoice.getUsedCriteria()[1]) {
            STDOUT.info("Autor: {} \n", CriteriaChoice.getAuthor());
        }
        if (CriteriaChoice.getUsedCriteria()[2]) {
            STDOUT.info("Wersja audio: {} \n\n", CriteriaChoice.getAudio() ? "tak" : "nie");
        }
    }


}
