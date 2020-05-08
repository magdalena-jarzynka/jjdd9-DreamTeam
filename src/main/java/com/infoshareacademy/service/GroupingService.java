package com.infoshareacademy.service;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;
import static com.infoshareacademy.menu.MenuUtils.cleanTerminal;

public class GroupingService {

    private final PageService pageService = new PageService();
    ListService listService = new ListService();
    private final GenreService genreService = new GenreService();
    private final UserInputService userInputService = new UserInputService();
    private final List<Genre> selectedGenres = new ArrayList<>();
    private int input;
    private long genreNumber;
    private long positionNumber;

    public void filterByCategory(Map<Long, Book> books) {
        List<Genre> genresList = genreService.findAllGenres();

        cleanTerminal();
        pageService.choosePagesCount(genresList.size());
        do {
            showGenreMenu(genresList, pageService);
            input = userInputService.getUserInput();
            switch (input) {
                case 1:
                    if (!pageService.isLastPage()) {
                        pageService.increasePagesCount();
                    }
                    break;
                case 2:
                    if (!pageService.isFirstPage()) {
                        pageService.decreasePagesCount();
                    }
                    break;
                case 3:
                    chooseGenres(genresList);
                    break;
                case 4:
                    showGroupedList(books);
                    return;
                case 0:
                    return;
            }
            cleanTerminal();
        } while (true);
    }

    private void showGenreMenu(List<Genre> genresList, PageService pageService) {
        genreNumber = pageService.findFirstPosition() + 1;
        genresList.stream()
                .skip(pageService.findFirstPosition())
                .limit(pageService.getPositionsPerPage())
                .forEach(genre ->
                        STDOUT.info("{}. {}\n", genreNumber++, genre.getName()));
        if(!selectedGenres.isEmpty()) {
            STDOUT.info("\nWybrane gatunki: \n");
            for (Genre genre: selectedGenres) {
                STDOUT.info("{}{}{}, ", ConsoleColors.YELLOW_BOLD.getColorType(),
                        genre.getName(), ConsoleColors.RESET.getColorType());
            }
        }
        if (pageService.isLastPage()) {
            STDOUT.info(PageService.LAST_PAGE);
        } else {
            STDOUT.info(PageService.NEXT_PAGE);
        }
        STDOUT.info("Wciśnij 3 aby wybrać gatunki, które cię interesują\n");
        STDOUT.info("Wciśnij 4 aby wyświetlić książki zgrupowane według wybranych gatunków\n");
    }

    private void chooseGenres(List<Genre> genresList) {
        STDOUT.info("Podaj numer wybranego gatunku lub 0 aby wyświetlić listę książek według wybranych gatunków\n");
        do {
            input = userInputService.getUserInput();
            if (input == 0) {
                return;
            } else if (input < genresList.size()) {
                selectedGenres.add(genresList.get(input));
                return;
            } else {
                STDOUT.info("Nieprawidłowy numer \n");
            }
        } while(true);
    }

    private void showGroupedList(Map<Long, Book> books) {
        PageService booksPageService = new PageService();
        booksPageService.setNumberOfChapters(selectedGenres.size());
        booksPageService.choosePagesCount(books.size());
        cleanTerminal();
        listService.showBookList(books, booksPageService.findFirstPosition(),
                booksPageService.findFirstPosition(), booksPageService.getPositionsPerPage());

        do {
            showGroupedBookList(books, booksPageService.findFirstPosition(),
                    booksPageService.getCurrentPageNumber(), booksPageService.getPositionsPerPage());
            if (booksPageService.isLastPage()) {
                STDOUT.info(PageService.LAST_PAGE);
            } else {
                STDOUT.info(PageService.NEXT_PAGE);
            }
            input = userInputService.getUserInput();
            switch (input) {
                case 1:
                    if (!booksPageService.isLastPage()) {
                        booksPageService.increasePagesCount();
                    }
                    break;
                case 2:
                    if (!booksPageService.isFirstPage()) {
                        booksPageService.decreasePagesCount();
                    }
                    break;
                case 0:
                    return;
            }
            cleanTerminal();
        } while (true);
    }

    private void showGroupedBookList(Map<Long, Book> books, long firstPositionOnPage, long positionNumber, int positionsPerPage) {
        this.positionNumber = positionNumber + 1;
        listService.getBookSet(books).stream()
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
}
