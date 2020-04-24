package com.infoshareacademy.menu;

import com.infoshareacademy.action.*;
import com.infoshareacademy.menu.item.BookListMenu;
import com.infoshareacademy.menu.item.MainMenu;
import com.infoshareacademy.menu.item.ReservationMenu;
import com.infoshareacademy.menu.item.SettingsMenu;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Menu {
    private static final String SHOW_MENU = "{}. {} \n";
    private static final String GO_BACK = "Wybierz 0 aby wrócić do głównego Menu. \n";
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String WRONG_NUMBER = "Proszę wpisać odpowiednią cyfrę.";
    private static final Scanner scanner = new Scanner(System.in);
    private int input;

    public Menu() {
    }

    public void run() {
        while (true) {
            input = openMainMenu();
            STDOUT.info("\n");
            if (input == 1) {
                chooseBooksMenu();
            }

            if (input == 2) {
                chooseReservationMenu();
            }

            if (input == 3) {
                chooseSettingsMenu();
            }

            if (input == 4) {
                STDOUT.info("Program został wyłączony. Miłego dnia!");
                break;
            }

        }

    }

    private void chooseBooksMenu() {
        int bookChoice = openBooksMenu();
        switch (bookChoice) {
            case 1:
                getBooksMenu(BookListMenu.BOOK_LIST);
                break;
            case 2:
                getBooksMenu(BookListMenu.SEARCH);
                break;
            case 3:
                getBooksMenu(BookListMenu.BOOK_DETAILS);
                break;
            default:
                input = openMainMenu();
                break;
        }
    }

    private void chooseReservationMenu() {
        int reservationChoice = openReservationMenu();
        switch (reservationChoice) {
            case 1:
                getReservationMenu(ReservationMenu.NEW_RESERVATION);
                break;
            case 2:
                getReservationMenu(ReservationMenu.CANCEL_RESERVATION);
                break;
            default:
                input = openMainMenu();
                break;
        }
    }

    private void chooseSettingsMenu() {
        int settingsChoice = openSettingsMenu();
        switch (settingsChoice) {
            case 1:
                getSettingsMenu(SettingsMenu.CONFIGURATIONS);
                break;
            case 2:
                getSettingsMenu(SettingsMenu.SORTING);
                break;
            default:
                getSettingsMenu(SettingsMenu.FORMAT);
                break;
        }
    }

    private int openMainMenu() {
        STDOUT.info("Witaj! Wybierz pozycję z Menu wpisując jej numer lub wciśnij ENTER by wyjść: \n");
        showMainMenu();
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }

    private int openBooksMenu() {
        showBooksMenu();
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }

    private int openReservationMenu() {
        showReservationMenu();
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }

    private int openSettingsMenu() {
        showSettingsMenu();
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }

    public void getBooksMenu(BookListMenu bookChoice) {
        BookListService bookListService = new BookListService();
        Search search = new Search();
        Details details = new Details();
        switch (bookChoice) {
            case BOOK_LIST:
                bookListService.run();
                break;
            case SEARCH:
                search.print();
                break;
            default:
                details.print();
                break;
        }
    }

    public void getReservationMenu(ReservationMenu reservationChoice) {
        NewReservation newReservation = new NewReservation();
        ReservationCancellation reservationCancellation = new ReservationCancellation();
        if (reservationChoice.equals(ReservationMenu.NEW_RESERVATION)) {
            newReservation.print();
        } else {
            reservationCancellation.print();
        }
    }

    public void getSettingsMenu(SettingsMenu settingsChoice) {
        Configurations configurations = new Configurations();
        SortingOptions sortingOptions = new SortingOptions();
        DataFormat dataFormat = new DataFormat();
        switch (settingsChoice) {
            case CONFIGURATIONS:
                configurations.print();
                break;
            case SORTING:
                sortingOptions.print();
                break;
            default:
                dataFormat.print();
                break;
        }
    }

    public void showMainMenu() {
        cleanTerminal();
        for (MainMenu mainMenu : MainMenu.values()) {
            int mainMenuPosition = mainMenu.ordinal() + 1;
            STDOUT.info(SHOW_MENU, mainMenuPosition, mainMenu.getMenuDescription());
        }
    }

    public void showBooksMenu() {
        cleanTerminal();
        STDOUT.info("W tej sekcji możesz przegladać zbiory książek. \n");
        STDOUT.info("Możesz również wyszukać daną pozycję i wyświetlić jej szczegóły. \n");
        STDOUT.info("Wybierz pozycję z menu wprowadzając jej numer. \n");
        for (BookListMenu bookListMenu : BookListMenu.values()) {
            int bookPosition = bookListMenu.ordinal() + 1;
            STDOUT.info(SHOW_MENU, bookPosition, bookListMenu.getBookDescription());
        }
        STDOUT.info(GO_BACK);
    }

    public void showReservationMenu() {
        cleanTerminal();
        STDOUT.info("W tej sekcji możesz dokonać rezerwacji lub anulować rezerwację.\n");
        STDOUT.info("Wybierz czynność, którą chcesz wykonać wprowadzając jej numer. \n");
        for (ReservationMenu reservationMenu : ReservationMenu.values()) {
            int reservationPosition = reservationMenu.ordinal() + 1;
            STDOUT.info(SHOW_MENU, reservationPosition, reservationMenu.getReservationValue());
        }
        STDOUT.info(GO_BACK);

    }

    public void showSettingsMenu() {
        cleanTerminal();
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
}
