package com.infoshareacademy.action.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Search {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private String author = "";
    private String title = "";
    private boolean audio = false;


    public void run() {
        do {
            showSearchPanel();
            switch (CriteriaChoice.userChoice()) {
                case 1: {
                    title = CriteriaChoice.title();
                    break;
                }
                case 2: {
                    author = CriteriaChoice.author();
                    break;
                }
                case 3: {
                    audio = CriteriaChoice.audio();
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
            STDOUT.info("Tytuł: {} \n", title);
        }
        if(CriteriaChoice.getUsedCriteria()[1]) {
            STDOUT.info("Autor: {} \n", author);
        }
        if (CriteriaChoice.getUsedCriteria()[2]) {
            STDOUT.info("Wersja audio: {} \n\n", audio ? "tak" : "nie");
        }
    }
}
