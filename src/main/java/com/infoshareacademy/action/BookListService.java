package com.infoshareacademy.action;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.menu.item.FavouritesMenu;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.service.ListService;
import com.infoshareacademy.service.PageService;

import java.util.Map;

import static com.infoshareacademy.menu.MenuUtils.*;

public class BookListService {

    public static final String SEE_DETAILS = "Wybierz 4 aby zobaczyć szczegóły wybranej pozycji.\n";
    private static final String GROUP_BY_CATEGORY = "Wybierz 5 aby pogrupować wyniki według gatunku.\n";

    public static final String LAST_PAGE = "\nTo ostatnia strona. " +
            "Wybierz 2 aby zobaczyć poprzednią stronę lub 0 aby wyjść do głównego menu. \n";
    public static final String NEXT_PAGE = "\nWybierz 1 aby zobaczyć następną stronę, 2 aby zobaczyć poprzednią lub 0 aby wyjść do " +
            "poprzedniego menu. \n";
    private int positionsPerPage;
    private int currentPageNumber;
    private FavouritesMenu favouritesMenu = new FavouritesMenu();
    UserInputService userInputService = new UserInputService();
    ListService listService = new ListService();
    private PageService pageService = new PageService();

    public BookListService() {
        this.currentPageNumber = 1;
    }

    public void run(Map<Long, Book> books) {
        pageService.choosePagesCount(books.size());
        do {
            cleanTerminal();
            getBooksList(books);
            int input;
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
                    input = userInputService.getUserInput();
                    favouritesMenu.add(input);
                    break;
                case 4:
                    listService.showBookDetails();
                    break;
                case 5:
                    listService.showGenresMenu();
                    break;
                case 0:
                    return;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

    public void getBooksList(Map<Long, Book> books) {
        cleanTerminal();
        //int numberOfPages = listService.getPagesCount(positionsPerPage, books.size());
        listService.showBookList(books, pageService.findFirstPosition(),
                pageService.findFirstPosition(), pageService.getPositionsPerPage());
        if (pageService.isLastPage()) {
            STDOUT.info(LAST_PAGE);
        } else {
            STDOUT.info(NEXT_PAGE);
        }
        STDOUT.info("\nWybierz 3 i numer ID, aby dodać pozycję do ulubionych.");
        STDOUT.info(SEE_DETAILS);
        STDOUT.info(GROUP_BY_CATEGORY);

        STDOUT.info("\n{}Strona {} z {}.{}\n",
                ConsoleColors.BLACK_UNDERLINED.getColorType(), pageService.getCurrentPageNumber(),
                pageService.getNumberOfPages(), ConsoleColors.RESET.getColorType());
    }
}
