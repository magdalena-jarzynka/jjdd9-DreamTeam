package com.infoshareacademy.menu;

import com.infoshareacademy.action.*;
import com.infoshareacademy.action.search.Search;
import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.menu.item.*;
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
    UserInputService userInputService = new UserInputService();

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
                    Breadcrumbs.getInstance().addBreadcrumb(MainMenu.BOOK_RESERVATION.getMenuDescription());
                    chooseReservationMenu();
                    break;
                case 3:
                    Breadcrumbs.getInstance().addBreadcrumb(MainMenu.FAVOURITES.getMenuDescription());
                    favouritesMenu.chooseFavouritesMenu();
                    break;
                case 4:
                    Breadcrumbs.getInstance().addBreadcrumb(MainMenu.SETTINGS.getMenuDescription());
                    chooseSettingsMenu();
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }
        while (true);
    }


    public void showMainMenu() {
        cleanTerminal();
        STDOUT.info("Witaj! Wybierz pozycję z Menu wpisując jej numer lub wybierz 5 by wyjść: \n");
        for (MainMenu mainMenu : MainMenu.values()) {
            int mainMenuPosition = mainMenu.ordinal() + 1;
            STDOUT.info(SHOW_MENU, mainMenuPosition, mainMenu.getMenuDescription());
        }
    }

    private void chooseBooksMenu() {
        do {
            STDOUT.info(Breadcrumbs.getInstance().displayBreadcrumb());
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
                STDOUT.info(Breadcrumbs.getInstance().displayBreadcrumb());
                bookListService.run(bookService.findAllBooks());
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


    private void chooseReservationMenu() {
        do {
            STDOUT.info(Breadcrumbs.getInstance().displayBreadcrumb());
            input = 0;
            showReservationMenu();
            input = userInputService.getUserInput();
            switch (input) {
                case 1:
                    getReservationMenu(ReservationMenu.NEW_RESERVATION);
                    break;
                case 2:
                    getReservationMenu(ReservationMenu.CANCEL_RESERVATION);
                    break;
                case 0:
                    Breadcrumbs.getInstance().removeBreadcrumb();
                    return;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

    public void getReservationMenu(ReservationMenu input) {
        NewReservation newReservation = new NewReservation();
        ReservationCancellation reservationCancellation = new ReservationCancellation();
        if (input.equals(ReservationMenu.NEW_RESERVATION)) {
            newReservation.print();
        } else {
            reservationCancellation.print();
        }
    }

    public void showReservationMenu() {
        cleanTerminal();
        STDOUT.info("REZERWACJA KSIĄŻKI \n\n");
        STDOUT.info("W tej sekcji możesz dokonać rezerwacji lub anulować rezerwację.\n");
        STDOUT.info("Wybierz czynność, którą chcesz wykonać wprowadzając jej numer. \n");
        for (ReservationMenu reservationMenu : ReservationMenu.values()) {
            int reservationPosition = reservationMenu.ordinal() + 1;
            STDOUT.info(SHOW_MENU, reservationPosition, reservationMenu.getReservationValue());
        }
        STDOUT.info(GO_BACK);
    }

    private void chooseSettingsMenu() {
        do {
            STDOUT.info(Breadcrumbs.getInstance().displayBreadcrumb());
            input = 0;
            showSettingsMenu();
            input = userInputService.getUserInput();
            switch (input) {
                case 1:
                    getSettingsMenu(SettingsMenu.CONFIGURATIONS);
                    break;
                case 2:
                    Breadcrumbs.getInstance().addBreadcrumb(SettingsMenu.SORTING.getSettingsValue());
                    getSettingsMenu(SettingsMenu.SORTING);
                    Breadcrumbs.getInstance().removeBreadcrumb();
                    break;
                case 3:
                    getSettingsMenu(SettingsMenu.FORMAT);
                    break;
                case 0:
                    Breadcrumbs.getInstance().removeBreadcrumb();
                    return;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

    public void getSettingsMenu(SettingsMenu input) {
        Configurations configurations = new Configurations();
        SortingOptions sortingOptions = new SortingOptions();
        DataFormat dataFormat = new DataFormat();
        switch (input) {
            case CONFIGURATIONS:
                configurations.print();
                break;
            case SORTING:
                sortingOptions.getSortingOptions();
                break;
            default:
                dataFormat.print();
        }
    }

    public void showSettingsMenu() {
        cleanTerminal();
        STDOUT.info("USTAWIENIA \n\n");
        STDOUT.info("W tej sekcji możesz dokonać konfiguracji sortowania i formatu wyświetlania daty. \n");
        STDOUT.info("Wybierz pozycję z menu wprowadzając jej numer. \n");
        for (SettingsMenu settingsMenu : SettingsMenu.values()) {
            int settingsPosition = settingsMenu.ordinal() + 1;
            STDOUT.info(SHOW_MENU, settingsPosition, settingsMenu.getSettingsValue());
        }
        STDOUT.info(GO_BACK);
    }

}
