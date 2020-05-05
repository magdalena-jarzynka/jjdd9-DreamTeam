package com.infoshareacademy.action;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.menu.item.FavouritesMenu;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.service.ListService;

import java.util.Map;

import static com.infoshareacademy.menu.MenuUtils.*;

public class BookListService {
    public static final String SEE_DETAILS = "Wybierz 4 aby zobaczyć szczegóły wybranej pozycji.";
    public static final String LAST_PAGE = "\n To ostatnia strona. " +
            "Wybierz 2 aby zobaczyć poprzednią stronę lub 0 aby wyjść do głównego menu. \n";
    public static final String NEXT_PAGE = "\n Wybierz 1 aby zobaczyć następną stronę, 2 aby zobaczyć poprzednią lub 0 aby wyjść do " +
            "poprzedniego menu. \n";
    private int positionsPerPage;
    private int currentPageNumber;
    ListService listService = new ListService();

    public BookListService() {
        this.currentPageNumber = 1;
    }

    public void run(Map<Long, Book> books) {
        STDOUT.info("\n Ile pozycji wyświetlić na jednej stronie? \n");
        positionsPerPage = listService.getPositionsPerPage();
        int numberOfPages = listService.getPagesCount(positionsPerPage, books.size());
        do {
            cleanTerminal();
            getBooksList(books);
            int input;
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
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

    public void getBooksList(Map<Long, Book> books) {
        cleanTerminal();
        int numberOfPages = listService.getPagesCount(positionsPerPage, books.size());
        long firstPositionOnPage = listService.findFirstPosition(currentPageNumber, positionsPerPage);
        listService.showBookList(books, firstPositionOnPage, positionsPerPage);
        if (currentPageNumber == numberOfPages) {
            STDOUT.info(LAST_PAGE);
            STDOUT.info(SEE_DETAILS);
            STDOUT.info("\n Wybierz 3 i numer ID, aby dodać pozycję do ulubionych.");


        } else {
            STDOUT.info(NEXT_PAGE);
            STDOUT.info(SEE_DETAILS);
        }

        STDOUT.info("\n Wybierz 3 i numer ID, aby dodać pozycję do ulubionych.");


        STDOUT.info("\n{}Strona {} z {}.{}\n", ConsoleColors.BLACK_UNDERLINED.getColorType(), currentPageNumber,
                numberOfPages, ConsoleColors.RESET.getColorType());
    }
}
