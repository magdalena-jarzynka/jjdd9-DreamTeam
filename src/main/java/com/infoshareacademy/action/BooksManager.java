package com.infoshareacademy.action;

import com.infoshareacademy.service.management.BookManagement;

import static com.infoshareacademy.input.UserInputService.getUserInput;
import static com.infoshareacademy.menu.MenuUtils.*;

public class BooksManager {

    private BookManagement bookManagement = new BookManagement();

    public void run() {
        STDOUT.info("Wprowadź 1, aby dodać książkę.\n");
        STDOUT.info("Wprowadź 2, aby usunąć książkę.\n");
        STDOUT.info("Wprowadź 3, aby zmodyfikować istniejącą książkę.\n");
        STDOUT.info("Wprowadź 0, aby powrócić do poprzedniego menu.\n");
        do {
            int input = getUserInput();
            switch (input) {
                case 1:
                    cleanTerminal();
                    bookManagement.addBookToRepository();
                    return;
                case 2:
                    cleanTerminal();
                    bookManagement.removeBookFromRepository();
                case 3:
                    cleanTerminal();
                    bookManagement.modifyBook();
                case 0:
                    return;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

}
