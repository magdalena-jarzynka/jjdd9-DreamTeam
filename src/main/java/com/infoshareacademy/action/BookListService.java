package com.infoshareacademy.action;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.menu.Menu;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BookListService implements ConsoleColors{
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final Scanner scanner = new Scanner(System.in);
    private static final String WRONG_NUMBER = "Proszę wpisać odpowiednią cyfrę.\n";
    private int input;
    private int positionsPerPage;
    private int currentPageNumber;
    private int numberOfPages;

    public BookListService() {
        this.currentPageNumber = 1;
        this.numberOfPages = 2;
        this.input = 0;
    }

    public int getUserInput() {
        String lineInput = scanner.nextLine();
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
        while (true) {
            getBooksList();
            input = getUserInput();
            if (input == 1 && currentPageNumber < numberOfPages) {
                currentPageNumber = currentPageNumber + 1;
            }
            if (input == 2 && currentPageNumber > 1) {
                currentPageNumber = currentPageNumber - 1;
            }
            if (input == 0) {
                break;
            }
            input = 5;
        }
    }

    public void getBooksList() {
        Menu menu = new Menu();
        menu.cleanTerminal();
        List<Book> bookList = BookRepository.getInstance().getBooks();
        if (positionsPerPage != 0) {
            numberOfPages = (int) Math.ceil((double) bookList.size() / positionsPerPage);
        }
        int firstPositionOnPage = (currentPageNumber - 1) * positionsPerPage;
        int lastPositionOnPage = firstPositionOnPage + positionsPerPage;
        for (int i = 0; i < positionsPerPage; i++) {
            if (lastPositionOnPage > bookList.size()) {
                lastPositionOnPage = lastPositionOnPage - 1;
            }
        }

        bookList = SortingOptions.sortList(bookList);

        for (int i = firstPositionOnPage; i < lastPositionOnPage; i++) {
            int positionNumber = i + 1;
            STDOUT.info("{}{}.Tytuł: {}{}{}{} \n", ConsoleColors.BLACK_BOLD, positionNumber, ConsoleColors.RESET, ConsoleColors.RED, bookList.get(i).getTitle(), ConsoleColors.RESET);
            STDOUT.info(" {} Autor: {}{}{}{} \n\n", ConsoleColors.BLACK_BOLD, ConsoleColors.RESET, ConsoleColors.BLUE, bookList.get(i).getAuthors(), ConsoleColors.RESET);
        }
        if (currentPageNumber == numberOfPages) {
            STDOUT.info("\n To ostatnia strona. Wybierz 2 aby zobaczyć poprzednią stronę lub 0 aby wyjść do głównego menu. \n\n");
        } else {
            STDOUT.info("\n Wybierz 1 aby zobaczyć następną stronę, 2 aby zobaczyć poprzednią lub 0 aby wyjść do głównego menu. \n\n");
        }
        STDOUT.info("\n{}Strona {} z {}.{}\n", ConsoleColors.BLACK_UNDERLINED, currentPageNumber, numberOfPages, ConsoleColors.RESET);
    }
}
