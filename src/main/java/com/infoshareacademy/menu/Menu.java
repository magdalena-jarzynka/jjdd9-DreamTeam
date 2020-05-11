package com.infoshareacademy.menu;

import com.infoshareacademy.action.BookListService;
import com.infoshareacademy.action.BooksManager;
import com.infoshareacademy.action.Configurations;
import com.infoshareacademy.action.SortingOptions;
import com.infoshareacademy.action.search.Search;
import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.menu.item.BookListMenu;
import com.infoshareacademy.menu.item.FavouritesMenu;
import com.infoshareacademy.menu.item.MainMenu;
import com.infoshareacademy.service.BookService;

import static com.infoshareacademy.menu.MenuUtils.*;

public class Menu {
    private static final String SHOW_MENU = "{}. {} \n";
    private static final String GO_BACK = "Wybierz 0 aby wrócić do głównego Menu. \n";
    private int input = 0;
    private BookService bookService = new BookService();
    private FavouritesMenu favouritesMenu = new FavouritesMenu();
    private BookListService bookListService = new BookListService();
    private BooksManager booksManager = new BooksManager();
    private UserInputService userInputService = new UserInputService();
    private SortingOptions sortingOptions = new SortingOptions();

    public void openMainMenu() {
        Configurations.setDefaultProperties();
        do {
            showMainMenu();
            input = userInputService.getUserInput();
            STDOUT.info("\n");
            switch (input) {
                case 1:
                    Breadcrumbs.getInstance().addBreadcrumb(MainMenu.BROWSE.getMenuDescription());
                    chooseBooksMenu();
                    break;
                case 2:
                    Breadcrumbs.getInstance().addBreadcrumb(MainMenu.FAVOURITES.getMenuDescription());
                    favouritesMenu.chooseFavouritesMenu();
                    break;
                case 3:
                    sortingOptions.getSortingOptions();
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }
        while (true);
    }

    public void showMainMenu() {
        cleanTerminal();
        STDOUT.info("Witaj! Wybierz pozycję z Menu wpisując jej numer lub wybierz 4 by wyjść: \n");
        for (MainMenu mainMenu : MainMenu.values()) {
            int mainMenuPosition = mainMenu.ordinal() + 1;
            STDOUT.info(SHOW_MENU, mainMenuPosition, mainMenu.getMenuDescription());
        }
    }

    private void chooseBooksMenu() {
        do {
            showBooksMenu();
            input = 0;
            input = userInputService.getUserInput();
            switch (input) {
                case 1:
                    Breadcrumbs.getInstance().addBreadcrumb(BookListMenu.BOOK_LIST.getBookDescription());
                    getBooksMenu(BookListMenu.BOOK_LIST);
                    break;
                case 2:
                    Breadcrumbs.getInstance().addBreadcrumb(BookListMenu.SEARCH.getBookDescription());
                    getBooksMenu(BookListMenu.SEARCH);
                    break;
                case 3:
                    Breadcrumbs.getInstance().addBreadcrumb(BookListMenu.BOOKS_MANAGEMENT.getBookDescription());
                    getBooksMenu(BookListMenu.BOOKS_MANAGEMENT);
                    break;
                case 0:
                    Breadcrumbs.getInstance().removeBreadcrumb();
                    return;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

    public void getBooksMenu(BookListMenu input) {
        Search search = new Search();
        switch (input) {
            case BOOK_LIST:
                bookListService.displayBookList(bookService.findAllBooks());
                break;
            case SEARCH:
                search.getSearchingCriteria();
                break;
            default:
                booksManager.openBooksManager();
        }
    }

    public void showBooksMenu() {
        cleanTerminal();
        STDOUT.info(Breadcrumbs.getInstance().displayBreadcrumb());
        STDOUT.info("PRZEGLĄDANIE ZBIORÓW \n\n");
        STDOUT.info("W tej sekcji możesz przeglądać zbiory książek. \n");
        STDOUT.info("Możesz również wyszukać daną pozycję i wyświetlić jej szczegóły. \n");
        STDOUT.info("Wybierz pozycję z menu wprowadzając jej numer. \n");
        for (BookListMenu bookListMenu : BookListMenu.values()) {
            int bookPosition = bookListMenu.ordinal() + 1;
            STDOUT.info(SHOW_MENU, bookPosition, bookListMenu.getBookDescription());
        }
        STDOUT.info(GO_BACK);
    }

}
