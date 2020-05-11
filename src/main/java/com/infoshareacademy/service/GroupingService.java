package com.infoshareacademy.service;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Genre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.infoshareacademy.menu.MenuUtils.*;

public class GroupingService {

    private final PageService genrePageService = new PageService();
    private final PageService booksPageService = new PageService();
    private ListService listService = new ListService();
    private final GenreService genreService = new GenreService();
    private final UserInputService userInputService = new UserInputService();
    private final List<Genre> selectedGenres = new ArrayList<>();
    private Map<Long, Book> filteredBooks = new HashMap<>();
    private int input;
    private long genreNumber;
    private long positionNumber;

    public void filterByCategory(Map<Long, Book> books) {
        List<Genre> genresList = genreService.findAllGenres();

        cleanTerminal();
        genrePageService.choosePagesCount();
        genrePageService.addChapter(genresList.size());
        do {
            showGenreMenu(genresList);
            input = userInputService.getUserInput();
            switch (input) {
                case 1:
                    if (!genrePageService.isLastPage()) {
                        genrePageService.increasePagesCount();
                    }
                    break;
                case 2:
                    if (!genrePageService.isFirstPage()) {
                        genrePageService.decreasePagesCount();
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
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
            cleanTerminal();
        } while (true);
    }

    private void showGenreMenu(List<Genre> genresList) {
        genreNumber = genrePageService.findFirstPosition() + 1;
        genresList.stream()
                .skip(genrePageService.findFirstPosition())
                .limit(genrePageService.getPositionsPerPage())
                .forEach(genre ->
                        STDOUT.info("{}. {}\n", genreNumber++, genre.getName()));
        if (!selectedGenres.isEmpty()) {
            STDOUT.info("\nWybrane gatunki: \n");
            for (Genre genre : selectedGenres) {
                STDOUT.info("{}{}{}, ", ConsoleColors.YELLOW_BOLD.getColorType(),
                        genre.getName(), ConsoleColors.RESET.getColorType());
            }
        }
        if (genrePageService.isLastPage()) {
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
            } else if (input < genresList.size() && !selectedGenres.contains(genresList.get(input - 1))) {
                selectedGenres.add(genresList.get(input - 1));
                return;
            } else {
                STDOUT.info("Nieprawidłowy numer \n");
            }
        } while (true);
    }

    private void showGroupedList(Map<Long, Book> books) {
        booksPageService.choosePagesCount();
        selectedGenres
                .forEach(genre -> {
                    filterBooks(books);
                    booksPageService.addChapter(filteredBooks.size());
                    booksPageService.increaseChaptersCount();
                });
        booksPageService.setCurrentChapterNumber(1);
        filterBooks(books);
        cleanTerminal();
        do {
            showGroupedBookList(booksPageService.findFirstPosition(),
                    booksPageService.getPositionsPerPage());
            if (booksPageService.isLastPage() && !booksPageService.isLastChapter()) {
                STDOUT.info(PageService.LAST_PAGE_NEXT_CHAPTER);
            } else if (booksPageService.isLastPage()) {
                STDOUT.info(PageService.LAST_PAGE);
            } else {
                STDOUT.info(PageService.NEXT_PAGE);
            }
            input = userInputService.getUserInput();
            switch (input) {
                case 1:
                    booksPageService.increasePagesCount();
                    filterBooks(books);
                    break;
                case 2:
                    booksPageService.decreasePagesCount();
                    if (booksPageService.isFirstPage()) {
                        filterBooks(books);
                    }
                    break;
                case 0:
                    return;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
            cleanTerminal();
        } while (true);
    }

    private void showGroupedBookList(long firstPositionOnPage, int positionsPerPage) {
        this.positionNumber = firstPositionOnPage + 1;
        if (booksPageService.isFirstPage()) {
            STDOUT.info("{}**** {} **** {}\n\n", ConsoleColors.YELLOW_BOLD.getColorType(),
                    selectedGenres.get(booksPageService.getCurrentChapterNumber() - 1).getName(),
                    ConsoleColors.RESET.getColorType());
        }
        if (filteredBooks.isEmpty()) {
            STDOUT.info("Brak książek z tego gatunku \n\n");
            return;
        }
        listService.getBookSet(filteredBooks).stream()
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

    public void filterBooks(Map<Long, Book> books) {
        filteredBooks = listService.getBookSet(books).stream()
                .filter(book ->
                        book.getValue().getGenres().get(0).getName()
                                .equals(selectedGenres.get(booksPageService.getCurrentChapterNumber() - 1).getName()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
