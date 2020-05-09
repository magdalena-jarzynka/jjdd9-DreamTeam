package com.infoshareacademy.action;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.menu.item.FavouritesMenu;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.service.GroupingService;
import com.infoshareacademy.service.ListService;
import com.infoshareacademy.service.PageService;

import java.util.Map;

import static com.infoshareacademy.menu.MenuUtils.*;

public class BookListService {

    public static final String SEE_DETAILS = "Wybierz 4 aby zobaczyć szczegóły wybranej pozycji.\n";
    private static final String GROUP_BY_CATEGORY = "Wybierz 5 aby pogrupować wyniki według gatunku.\n";
    private static final int INCREASE_PAGE_COUNT = 1;
    private static final int DECREASE_PAGE_COUNT = 2;
    private FavouritesMenu favouritesMenu = new FavouritesMenu();
    UserInputService userInputService = new UserInputService();
    ListService listService = new ListService();
    private PageService pageService = new PageService();

    public void run(Map<Long, Book> books) {
        pageService.choosePagesCount();
        pageService.addChapter(books.size());
        do {
            cleanTerminal();
            getBooksList(books);
            int input;
            input = userInputService.getUserInput();
            switch (input) {
                case INCREASE_PAGE_COUNT:
                    if (!pageService.isLastPage()) {
                        pageService.increasePagesCount();
                    }
                    break;
                case DECREASE_PAGE_COUNT:
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
                    GroupingService groupingService = new GroupingService();
                    groupingService.filterByCategory(books);
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
        listService.showBookList(books, pageService.findFirstPosition(), pageService.getPositionsPerPage());
        if (pageService.isLastPage()) {
            STDOUT.info(PageService.LAST_PAGE);
        } else {
            STDOUT.info(PageService.NEXT_PAGE);
        }
        STDOUT.info("\nWybierz 3 i numer ID, aby dodać pozycję do ulubionych.\n");
        STDOUT.info(SEE_DETAILS);
        STDOUT.info(GROUP_BY_CATEGORY);

        STDOUT.info("\n{}Strona {} z {}.{}\n",
                ConsoleColors.BLACK_UNDERLINED.getColorType(), pageService.getCurrentPageNumber(),
                pageService.getNumberOfPages(), ConsoleColors.RESET.getColorType());
    }
}
