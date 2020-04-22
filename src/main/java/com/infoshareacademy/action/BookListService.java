package com.infoshareacademy.action;

import com.infoshareacademy.Book;
import com.infoshareacademy.ConsoleColors;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class BookListService {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final Scanner scanner = new Scanner(System.in);
    private static final String WRONG_NUMBER = "Proszę wpisać odpowiednią cyfrę.";
    private static int input = 0;
    private static int positionsPerPage;
    private static int pageNumber;

    public BookListService() {
    }

    public int getUserInput() {
        String lineInput = scanner.nextLine();
        int input = 0;
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }

    public void run() {
        STDOUT.info("\n Proszę wpisać ile rekordów ma być wyświetlane na ekranie: \n");
        positionsPerPage = getUserInput();
        pageNumber = 1;
        while (true) {
            getBooksList();
            input = getUserInput();
            if (input == 1) {
                pageNumber = pageNumber + 1;
            }
            if (input == 0) {
                break;
            }
        }
    }

    public void getBooksList() {
        Book book = new Book();
        ArrayList<Book> books = book.getBook();
        int numberOfPages = 0;
        if (positionsPerPage != 0) {
            numberOfPages = (int) Math.ceil((double) books.size() / positionsPerPage);
        }
        int firstPositionOnPage = (pageNumber - 1) * positionsPerPage;
        int lastPositionOnPage = firstPositionOnPage + positionsPerPage;
        for (int i = 0; i < positionsPerPage; i++) {
            if (lastPositionOnPage > books.size()) {
                lastPositionOnPage = lastPositionOnPage - 1;
            }
        }
        for (int i = firstPositionOnPage; i < lastPositionOnPage; i++) {
            int positionNumber = i + 1;
            STDOUT.info("{}{}.Tytuł: {}{}{}{} \n", ConsoleColors.BLACK_BOLD, String.valueOf(positionNumber), ConsoleColors.RESET, ConsoleColors.RED, books.get(i).getTitle(), ConsoleColors.RESET);
            STDOUT.info(" {} Autor: {}{}{}{} \n\n", ConsoleColors.BLACK_BOLD, ConsoleColors.RESET, ConsoleColors.BLUE, books.get(i).getAuthor(), ConsoleColors.RESET);
        }
        if (pageNumber == numberOfPages) {
            STDOUT.info("\n To ostatnia strona. Naciśnij 0 aby wyjść do głównego menu. \n\n");
        } else {
            STDOUT.info("\n Wybierz 1 aby zobaczyć następną stronę lub 0 aby wyjść do głównego menu. \n\n");
        }
    }
}