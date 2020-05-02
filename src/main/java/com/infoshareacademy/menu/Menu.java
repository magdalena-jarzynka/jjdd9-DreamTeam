package com.infoshareacademy.menu;

import com.infoshareacademy.action.*;
import com.infoshareacademy.action.search.Search;
import com.infoshareacademy.menu.item.BookListMenu;
import com.infoshareacademy.menu.item.MainMenu;
import com.infoshareacademy.menu.item.ReservationMenu;
import com.infoshareacademy.menu.item.SettingsMenu;
import com.infoshareacademy.repository.BookRepository;
import com.infoshareacademy.service.BookService;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class Menu {
    private static final String SHOW_MENU = "{}. {} \n";
    private static final String GO_BACK = "Wybierz 0 aby wrócić do głównego Menu. \n";
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String WRONG_NUMBER = "Proszę wpisać odpowiednią cyfrę. \n";
    private static final Scanner scanner = new Scanner(System.in);
    private int input = 0;

    public void run() {
        Configurations.setDefaultProperties();
        do {
            input = 0;
            showMainMenu();
            getUserInput();
            STDOUT.info("\n");
            switch (input) {
                case 1:
                    chooseBooksMenu();
                    break;
                case 2:
                    chooseReservationMenu();
                    break;
                case 3:
                    chooseSettingsMenu();
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
            input = getUserInput();
            switch (input) {
                case 1:
                    getBooksMenu(BookListMenu.BOOK_LIST);
                    break;
                case 2:
                    getBooksMenu(BookListMenu.SEARCH);
                    break;
                case 3:
                    getBooksMenu(BookListMenu.BOOK_DETAILS);
                    break;
                case 0:
                    return;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

    public void getBooksMenu(BookListMenu input) {
        BookListService bookListService = new BookListService();
        Search search = new Search();
        Details details = new Details();
        switch (input) {
            case BOOK_LIST:
                BookService bookService = new BookService();
                bookListService.run(bookService.findAllBooks());
                break;
            case SEARCH:
                search.run();
                break;
            default:
                details.print();
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
            input = 0;
            showReservationMenu();
            input = getUserInput();
            switch (input) {
                case 1:
                    getReservationMenu(ReservationMenu.NEW_RESERVATION);
                    break;
                case 2:
                    getReservationMenu(ReservationMenu.CANCEL_RESERVATION);
                    break;
                case 0:
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
            input = 0;
            showSettingsMenu();
            input = getUserInput();
            switch (input) {
                case 1:
                    getSettingsMenu(SettingsMenu.CONFIGURATIONS);
                    break;
                case 2:
                    getSettingsMenu(SettingsMenu.SORTING);
                    break;
                case 3:
                    getSettingsMenu(SettingsMenu.FORMAT);
                    break;
                case 0:
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
                sortingOptions.run();
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

    public void cleanTerminal() {
        STDOUT.info("\033\143");
    }

    private int getUserInput() {
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
            input = getUserInput();
        }
        return input;
    }

}
