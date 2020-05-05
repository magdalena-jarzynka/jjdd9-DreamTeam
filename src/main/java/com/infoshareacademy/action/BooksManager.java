package com.infoshareacademy.action;

import com.infoshareacademy.service.management.BookManagement;

import static com.infoshareacademy.input.UserInputService.getUserInput;
import static com.infoshareacademy.menu.MenuUtils.*;

public class BooksManager {

    BookManagement bookManagement = new BookManagement();

    public void run() {
        STDOUT.info("Naciśnij 1, aby dodać książkę");
        do {
            cleanTerminal();
            int a = 0;
            int input = getUserInput();
            switch (input) {
                case 1:
                    bookManagement.run();
                    break;
                case 0:
                    return;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

}
