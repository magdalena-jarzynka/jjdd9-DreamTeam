package com.infoshareacademy.service;

import com.infoshareacademy.menu.item.FavouritesMenu;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

import static com.infoshareacademy.menu.MenuUtils.*;

public class ListService {
    private static final Scanner scanner = new Scanner(System.in);
    private int input;
    private int positionsPerPage;
    private int currentPageNumber;
    private int numberOfPages;
    private long positionNumber;
    private long firstPositionOnPage;
    private long lastPositionOnPage;

    public int getUserInput() {
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
            input = getUserInput();
        }
        if (input < 0) {
            STDOUT.info(WRONG_NUMBER);
            input = getUserInput();
        }
        return input;
    }

    public int getPositionsPerPage() {
        return getUserInput();
    }

    public int getPagesCount(int positionsPerPage, int numberOfBooks) {
        BookService bookService = new BookService();
        if (positionsPerPage > 0) {
            numberOfPages = (int) Math.ceil((double) numberOfBooks / positionsPerPage);
        }
        return numberOfPages;
    }

    public long findFirstPosition(int currentPageNumber, int positionsPerPage) {
        return ((long) currentPageNumber - 1) * positionsPerPage;
    }

    public long findLastPosition(int numberOfBooks) {
        BookService bookService = new BookService();
        lastPositionOnPage = firstPositionOnPage + positionsPerPage;
        if (lastPositionOnPage > numberOfBooks) {
            lastPositionOnPage = numberOfBooks % positionsPerPage;
        }
        return lastPositionOnPage;
    }

    public void showBookDetails() {
        BookService bookService = new BookService();
        STDOUT.info("Wybierz ID książki, by zobaczyć jej szczegóły.");
        int id = getUserInput();
        cleanTerminal();
        STDOUT.info(bookService.getBookDetails((long) id));
        do {
            STDOUT.info("Wybierz 0, aby powrócić do przeglądania pozycji.");
            FavouritesMenu favouritesMenu = new FavouritesMenu();
            favouritesMenu.printAction(id);
            input = getUserInput();
            switch (input) {
                case 0:
                    return;
                case 1:
                    favouritesMenu.performAction(id);
                    break;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

    public long findPositionNumber(long firstPositionOnPage) {
        return firstPositionOnPage + 1;
    }
}
