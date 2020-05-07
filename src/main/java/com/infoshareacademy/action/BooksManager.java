package com.infoshareacademy.action;

import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.service.management.BooksOperations;

import static com.infoshareacademy.menu.MenuUtils.*;

public class BooksManager {

    private BooksOperations booksOperations = new BooksOperations();
    private UserInputService userInputService = new UserInputService();

    public void openBooksManager() {
        STDOUT.info("Wprowadź 1, aby dodać książkę.\n");
        STDOUT.info("Wprowadź 2, aby usunąć książkę.\n");
        STDOUT.info("Wprowadź 3, aby zmodyfikować istniejącą książkę.\n");
        STDOUT.info("Wprowadź 0, aby powrócić do poprzedniego menu.\n");
        do {
            int input = userInputService.getUserInput();
            switch (input) {
                case 1:
                    cleanTerminal();
                    booksOperations.addBookToRepository();
                    return;
                case 2:
                    cleanTerminal();
                    booksOperations.removeBookFromRepository();
                    return;
                case 3:
                    cleanTerminal();
                    booksOperations.modifyBook();
                    return;
                case 0:
                    return;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

}
