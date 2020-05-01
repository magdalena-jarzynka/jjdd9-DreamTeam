package com.infoshareacademy.action;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.menu.Menu;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.service.BookService;
import com.infoshareacademy.service.ListService;
import com.infoshareacademy.service.sorting.SortByAuthorStrategy;
import com.infoshareacademy.service.sorting.SortStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.SortedSet;

public class BookListService {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private int input;
    private int positionsPerPage;
    private int currentPageNumber;
    private int numberOfPages;
    private long positionNumber;
    private long firstPositionOnPage;
    private long lastPositionOnPage;

    public BookListService() {
        this.currentPageNumber = 1;
        this.numberOfPages = 2;
    }

    public void run() {
        ListService listService = new ListService();
        STDOUT.info("\n Ile pozycji wyświetlić na jednej stronie? \n");
        positionsPerPage = listService.getPositionsPerPage();
        do {
            Menu menu = new Menu();
            menu.cleanTerminal();
            getBooksList();
            input = 0;
            input = listService.getUserInput();
            switch (input) {
                case 1:
                    if (currentPageNumber < numberOfPages) {
                        currentPageNumber = currentPageNumber + 1;
                    }
                    break;
                case 2:
                    if (currentPageNumber > 1) {
                        currentPageNumber = currentPageNumber - 1;
                    }
                    break;
                case 0:
                    return;
                default:
                    input = listService.getUserInput();
            }
        } while (true);
    }

    public void getBooksList() {
        ListService listService = new ListService();
        BookService bookService = new BookService();
        Menu menu = new Menu();
        menu.cleanTerminal();
        numberOfPages = listService.getPagesCount(positionsPerPage);
        firstPositionOnPage = listService.findFirstPosition(currentPageNumber, positionsPerPage);
        lastPositionOnPage = listService.findLastPosition();
        SortStrategy sortStrategy = new SortByAuthorStrategy();
        SortedSet<Map.Entry<Long, Book>> booksSet =
                sortStrategy.getSortedList(bookService.findAllBooks());
        positionNumber = listService.findPositionNumber(firstPositionOnPage);
        booksSet.stream()
                .skip(firstPositionOnPage)
                .limit(positionsPerPage)
                .forEach(b ->
                        STDOUT.info("{}{}.Tytuł: {}{} \n {} Autor: {}{} \n {} ID: {}{}{} \n\n",
                                ConsoleColors.BLACK_BOLD.getColorType(), positionNumber++,
                                ConsoleColors.RED.getColorType(), b.getValue().getTitle(),
                                ConsoleColors.BLACK_BOLD.getColorType(), ConsoleColors.BLUE.getColorType(),
                                b.getValue().getAuthors().get(0).getName(), ConsoleColors.BLACK_BOLD.getColorType(),
                                ConsoleColors.YELLOW_BOLD.getColorType(), b.getKey(),
                                ConsoleColors.RESET.getColorType(), b));

        if (currentPageNumber == numberOfPages) {
            STDOUT.info("\n To ostatnia strona. " +
                    "Wybierz 2 aby zobaczyć poprzednią stronę lub 0 aby wyjść do głównego menu. \n\n");
        } else {
            STDOUT.info("\n Wybierz 1 aby zobaczyć następną stronę, 2 aby zobaczyć poprzednią lub 0 aby wyjść do " +
                    "poprzedniego menu. \n\n");
        }
        STDOUT.info("\n{}Strona {} z {}.{}\n", ConsoleColors.BLACK_UNDERLINED.getColorType(), currentPageNumber,
                numberOfPages, ConsoleColors.RESET.getColorType());
    }
}
