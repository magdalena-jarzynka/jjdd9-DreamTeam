package com.infoshareacademy.service;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.action.Configurations;
import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.menu.Breadcrumbs;
import com.infoshareacademy.menu.item.BookListMenu;
import com.infoshareacademy.menu.item.FavouritesMenu;
import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.service.sorting.SortByAuthorStrategy;
import com.infoshareacademy.service.sorting.SortByTitleStrategy;

import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.stream.Collectors;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;
import static com.infoshareacademy.menu.MenuUtils.WRONG_NUMBER;

public class ListService {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String AUTHOR = "AUTHOR";
    private int input;
    private int numberOfPages;
    private long positionNumber;
    private FavouritesMenu favouritesMenu = new FavouritesMenu();
    private BookService bookService = new BookService();
    private UserInputService userInputService = new UserInputService();


    public int getPositionsPerPage() {
        return userInputService.getUserInput();
    }

    public int getPagesCount(int positionsPerPage, int numberOfBooks) {
        if (positionsPerPage > 0) {
            numberOfPages = (int) Math.ceil((double) numberOfBooks / positionsPerPage);
        }
        return numberOfPages;
    }

    public long findFirstPosition(int currentPageNumber, int positionsPerPage) {
        return ((long) currentPageNumber - 1) * positionsPerPage;
    }

    public void showBookDetails() {
        STDOUT.info("Wybierz ID książki, by zobaczyć jej szczegóły lub naciśnij 0, aby wrócić do poprzedniego widoku.");
        long id;
        do {
            id = userInputService.getUserInput();
            if (id == 0) {
                return;
            } else if (bookService.findAllBooks().containsKey(id)) {
                Breadcrumbs.getInstance().addBreadcrumb(BookListMenu.BOOKS_MANAGEMENT.getBookDescription());
                STDOUT.info(Breadcrumbs.getInstance().displayBreadcrumb());
                break;
            } else {
                STDOUT.info("\nPozycja o podanym ID nie istnieje\n");
            }
        } while (true);
        STDOUT.info(bookService.getBookDetails(id));
        do {
            STDOUT.info("Wybierz 0, aby powrócić do przeglądania pozycji.");
            favouritesMenu.printAction(id);
            input = userInputService.getUserInput();
            switch (input) {
                case 0:
                    Breadcrumbs.getInstance().removeBreadcrumb();
                    return;
                case 1:
                    favouritesMenu.performAction(id);
                    break;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

    public void showBookList(Map<Long, Book> books, long firstPositionOnPage, int positionsPerPage) {
        positionNumber = findPositionNumber(firstPositionOnPage);
        getBookSet(books).stream()
                .skip(firstPositionOnPage)
                .limit(positionsPerPage)
                .forEach(book ->
                        STDOUT.info("{}{}.Tytuł: {}{} \n {} Autor: {}{} \n {} ID: {}{}{} \n\n",
                                ConsoleColors.BLACK_BOLD.getColorType(), positionNumber++,
                                ConsoleColors.RED.getColorType(), book.getValue().getTitle(),
                                ConsoleColors.BLACK_BOLD.getColorType(), ConsoleColors.BLUE.getColorType(),
                                book.getValue().getAuthors().stream()
                                        .filter(authors -> !(book.getValue().getAuthors()).isEmpty())
                                        .map(Author::getName)
                                        .collect(Collectors.joining(", ")),
                                ConsoleColors.BLACK_BOLD.getColorType(), ConsoleColors.YELLOW_BOLD.getColorType(),
                                book.getKey(), ConsoleColors.RESET.getColorType()));
    }

    public long findPositionNumber(long firstPositionOnPage) {
        return firstPositionOnPage + 1;
    }

    public SortedSet<Map.Entry<Long, Book>> getBookSet(Map<Long, Book> books) {
        return isSortByAuthor() ? new SortByAuthorStrategy().getSortedList(books) : new SortByTitleStrategy().getSortedList(books);
    }

    private boolean isSortByAuthor() {
        return (Configurations.getProperties().getProperty("sortingBy").equals(AUTHOR));
    }
}
