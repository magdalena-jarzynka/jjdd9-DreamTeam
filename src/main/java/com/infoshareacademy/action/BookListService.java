package com.infoshareacademy.action;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.menu.item.FavouritesMenu;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.service.ListService;
import com.infoshareacademy.service.sorting.SortByAuthorStrategy;
import com.infoshareacademy.service.sorting.SortByTitleStrategy;
import com.infoshareacademy.service.sorting.SortStrategy;

import java.util.Map;
import java.util.SortedSet;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;
import static com.infoshareacademy.menu.MenuUtils.cleanTerminal;

public class BookListService {
    public static final String SEE_DETAILS = "Wybierz 4 aby zobaczyć szczegóły wybranej pozycji.";
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

    public void run(Map<Long, Book> books) {
        ListService listService = new ListService();
        STDOUT.info("\n Ile pozycji wyświetlić na jednej stronie? \n");
        positionsPerPage = listService.getPositionsPerPage();
        do {
            cleanTerminal();
            getBooksList(books);
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
                case 3:
                    input = listService.getUserInput();
                    FavouritesMenu favouritesMenu = new FavouritesMenu();
                    favouritesMenu.add(input);
                    break;
                case 4:
                    listService.showBookDetails();
                    break;
                case 0:
                    return;
                default:
                    input = listService.getUserInput();
            }
        } while (true);
    }

    public void getBooksList(Map<Long, Book> books) {
        ListService listService = new ListService();
        cleanTerminal();
        numberOfPages = listService.getPagesCount(positionsPerPage, books.size());
        firstPositionOnPage = listService.findFirstPosition(currentPageNumber, positionsPerPage);
        lastPositionOnPage = listService.findLastPosition(books.size());

        SortStrategy sortStrategy;
        if (Configurations.getProperties().getProperty("sortingBy").equals("AUTHOR")) {
            sortStrategy = new SortByAuthorStrategy();
        } else {
            sortStrategy = new SortByTitleStrategy();
        }

        SortedSet<Map.Entry<Long, Book>> booksSet =
                sortStrategy.getSortedList(books);
        positionNumber = listService.findPositionNumber(firstPositionOnPage);
        booksSet.stream()
                .skip(firstPositionOnPage)
                .limit(positionsPerPage)
                .forEach(book ->
                        STDOUT.info("{}{}.Tytuł: {}{} \n {} Autor: {}{} \n {} ID: {}{}{} \n\n",
                                ConsoleColors.BLACK_BOLD.getColorType(), positionNumber++,
                                ConsoleColors.RED.getColorType(), book.getValue().getTitle(),
                                ConsoleColors.BLACK_BOLD.getColorType(), ConsoleColors.BLUE.getColorType(),
                                book.getValue().getAuthors().get(0).getName(), ConsoleColors.BLACK_BOLD.getColorType(),
                                ConsoleColors.YELLOW_BOLD.getColorType(), book.getKey(),
                                ConsoleColors.RESET.getColorType()));

        if (currentPageNumber == numberOfPages) {
            STDOUT.info("\n To ostatnia strona. " +
                    "Wybierz 2 aby zobaczyć poprzednią stronę lub 0 aby wyjść do głównego menu. \n");
            STDOUT.info(SEE_DETAILS);
            STDOUT.info("\n Wybierz 3 i numer ID, aby dodać pozycję do ulubionych.");


        } else {
            STDOUT.info("\n Wybierz 1 aby zobaczyć następną stronę, 2 aby zobaczyć poprzednią lub 0 aby wyjść do " +
                    "poprzedniego menu. \n");
            STDOUT.info(SEE_DETAILS);
        }

        STDOUT.info("\n Wybierz 3 i numer ID, aby dodać pozycję do ulubionych.");


        STDOUT.info("\n{}Strona {} z {}.{}\n", ConsoleColors.BLACK_UNDERLINED.getColorType(), currentPageNumber,
                numberOfPages, ConsoleColors.RESET.getColorType());
    }
}
