package com.infoshareacademy.menu;

import com.infoshareacademy.action.*;
import com.infoshareacademy.menu.item.BookListMenu;
import com.infoshareacademy.menu.item.MainMenu;
import com.infoshareacademy.menu.item.ReservationMenu;
import com.infoshareacademy.menu.item.SettingsMenu;
import com.infoshareacademy.apublicction.Search;
import copublicm.infoshareacademy.action.AddReservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Menu {
    private static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    private BookListMenu bookChoice;
    private ReservationMenu reservationChoice;
    private SettingsMenu settingsChoice;

    public void booksMenu(BookListMenu bookChoice) {
        this.bookChoice = bookChoice;
        switch (bookChoice) {
            case BOOK_LIST:
                FullList.print();
                break;
            case SEARCH:
                Search.print();
                break;
            case BOOK_DETAILS:
                Details.print();
                break;
        }
    }

    public void reservationMenu(ReservationMenu reservationChoice) {
        this.reservationChoice = reservationChoice;
        switch (reservationChoice) {
            case NEW_RESERVATION:
                AddReservation.print();
                break;
            case CANCEL_RESERVATION:
                DeleteReservation.print();
                break;
        }
    }

    public void settingsMenu(SettingsMenu settingsChoice) {
        this.settingsChoice = settingsChoice;
        switch (settingsChoice) {
            case CONFIGURATIONS:
                ChangeConfiguration.print();
                break;
            case SORTING:
                SortingOptions.print();
                break;
            case FORMAT:
                DataFormat.print();
                break;
        }
    }

    public static void showMenu() {
        for (MainMenu mainMenu : MainMenu.values()) {
            int mainMenuPosition = mainMenu.ordinal() + 1;
            STDOUT.info("{}. {} \n", mainMenuPosition, mainMenu.getMenuDescription());
        }
    }

    public static void showBooksMenu() {
        STDOUT.info("W tej sekcji możesz przegladać zbiory książek.");
        STDOUT.info("Możesz również wyszukać danej pozycji i wyświetlić jej szczegóły.");
        STDOUT.info("Wybierz pozycję z menu wprowadzając jej numer. \n");
        for (BookListMenu bookListMenu : BookListMenu.values()) {
            int bookPosition = bookListMenu.ordinal() + 1;
            STDOUT.info("{}. {} \n", bookPosition, bookListMenu.getBookDescription());
        }
        STDOUT.info("Wybierz 0 aby wrócić do głównego Menu.");
    }

    public static void showReservationMenu() {
        STDOUT.info("W tej sekcji możesz dokonać rezerwacji lub anulować rezerwację.");
        STDOUT.info("Wybierz czynność, którą chcesz wykonać wprowadzając jej numer. \n");
        for (ReservationMenu reservationMenu : ReservationMenu.values()) {
            int reservationPosition = reservationMenu.ordinal() + 1;
            STDOUT.info("{}. {} \n", reservationPosition, reservationMenu.getReservationValue());
        }
        STDOUT.info("Wybierz 0 aby wrócić do głównego Menu.");

    }

    public static void showSettingsMenu() {
        STDOUT.info("W tej sekcji możesz dokonać konfiguracji sortowania i formatu wyświetlania daty.");
        STDOUT.info("Wybierz pozycję z menu wprowadzając jej numer. \n");
        for (SettingsMenu settingsMenu : SettingsMenu.values()) {
            int settingsPosition = settingsMenu.ordinal() + 1;
            STDOUT.info("{}. {} \n", settingsPosition, settingsMenu.getSettingsValue());
        }
        STDOUT.info("Wybierz 0 aby wrócić do głównego Menu.");
    }
}
