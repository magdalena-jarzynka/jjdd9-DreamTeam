package com.infoshareacademy.action;

import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.menu.Breadcrumbs;
import com.infoshareacademy.service.management.BooksOperations;

import static com.infoshareacademy.menu.MenuUtils.*;

public class BooksManager {

    private BooksOperations booksOperations = new BooksOperations();
    private UserInputService userInputService = new UserInputService();

    public void openBooksManager() {
        STDOUT.info(Breadcrumbs.getInstance().displayBreadcrumb());
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
                    Breadcrumbs.getInstance().removeBreadcrumb();
                    return;
                case 2:
                    cleanTerminal();
                    booksOperations.removeBookFromRepository();
                    Breadcrumbs.getInstance().removeBreadcrumb();
                    return;
                case 3:
                    cleanTerminal();
                    booksOperations.modifyBook();
                    Breadcrumbs.getInstance().removeBreadcrumb();
                    return;
                case 0:
                    Breadcrumbs.getInstance().removeBreadcrumb();
                    return;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

}
