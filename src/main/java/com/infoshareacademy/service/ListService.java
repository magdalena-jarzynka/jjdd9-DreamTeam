package com.infoshareacademy.service;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.action.Configurations;
import com.infoshareacademy.menu.item.FavouritesMenu;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Genre;
import com.infoshareacademy.service.sorting.SortByAuthorStrategy;
import com.infoshareacademy.service.sorting.SortByTitleStrategy;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;

import static com.infoshareacademy.menu.MenuUtils.*;

public class ListService {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String AUTHOR = "AUTHOR";
    private int input;
    private long positionNumber;
    private int genreNumber;
    private FavouritesMenu favouritesMenu = new FavouritesMenu();

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

    public void showBookDetails() {
        BookService bookService = new BookService();
        STDOUT.info("Wybierz ID książki, by zobaczyć jej szczegóły.");
        int id = getIdChoice();
        cleanTerminal();
        STDOUT.info(bookService.getBookDetails((long) id));
        do {
            STDOUT.info("Wybierz 0, aby powrócić do przeglądania pozycji.");
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

    public void showGenresMenu() {
        GenreService genreService = new GenreService();
        List<Genre> genresList = genreService.findAllGenres();
        genreNumber = 1;

        genresList
                .forEach(genre ->
                        STDOUT.info("{}. {}\n", genreNumber++, genre.getName()));
    }

    public void showBookList(Map<Long, Book> books, long firstPositionOnPage, long positionNumber, int positionsPerPage) {
        this.positionNumber = positionNumber + 1;
        getBookSet(books).stream()
                .skip(firstPositionOnPage)
                .limit(positionsPerPage)
                .forEach(book ->
                        STDOUT.info("{}{}.Tytuł: {}{} \n {} Autor: {}{} \n {} ID: {}{}{} \n\n",
                                ConsoleColors.BLACK_BOLD.getColorType(), this.positionNumber++,
                                ConsoleColors.RED.getColorType(), book.getValue().getTitle(),
                                ConsoleColors.BLACK_BOLD.getColorType(), ConsoleColors.BLUE.getColorType(),
                                book.getValue().getAuthors().get(0).getName(), ConsoleColors.BLACK_BOLD.getColorType(),
                                ConsoleColors.YELLOW_BOLD.getColorType(), book.getKey(),
                                ConsoleColors.RESET.getColorType()));
    }

    public int getIdChoice() {
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
            input = getIdChoice();
        }
        if (input <= 0) {
            STDOUT.info(WRONG_NUMBER);
            input = getIdChoice();
        }
        return input;
    }

    public SortedSet<Map.Entry<Long, Book>> getBookSet(Map<Long, Book> books) {
        return isSortByAuthor() ? new SortByAuthorStrategy().getSortedList(books) : new SortByTitleStrategy().getSortedList(books);
    }

    private boolean isSortByAuthor() {
        return (Configurations.getProperties().getProperty("sortingBy").equals(AUTHOR));
    }
}
