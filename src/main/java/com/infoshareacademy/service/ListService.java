package com.infoshareacademy.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ListService {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final Scanner scanner = new Scanner(System.in);
    private static final String WRONG_NUMBER = "Proszę wpisać odpowiednią cyfrę.\n\n";
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

    public int getPagesCount(int positionsPerPage) {
        BookService bookService = new BookService();
        if (positionsPerPage > 0) {
            numberOfPages = (int) Math.ceil((double) bookService.getBooksSize() / positionsPerPage);
        }
        return numberOfPages;
    }

    public long findFirstPosition(int currentPageNumber, int positionsPerPage) {
        return ((long) currentPageNumber - 1) * positionsPerPage;
    }

    public long findLastPosition() {
        BookService bookService = new BookService();
        lastPositionOnPage = firstPositionOnPage + positionsPerPage;
        if (lastPositionOnPage > bookService.getBooksSize()) {
            lastPositionOnPage = bookService.getBooksSize() % positionsPerPage;
        }
        return lastPositionOnPage;
    }

    public long findPositionNumber(long firstPositionOnPage) {
        return firstPositionOnPage + 1;
    }
}
