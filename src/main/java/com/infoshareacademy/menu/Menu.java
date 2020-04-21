package com.infoshareacademy.menu;

import com.infoshareacademy.action.*;
import com.infoshareacademy.menu.item.BookListMenu;
import com.infoshareacademy.menu.item.MainMenu;
import com.infoshareacademy.menu.item.ReservationMenu;
import com.infoshareacademy.menu.item.SettingsMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Menu {
    private static final String SHOW_MENU = "{}. {} \n";
    private static final String GO_BACK = "Wybierz 0 aby wrócić do głównego Menu. \n";
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void booksMenu(BookListMenu bookChoice) {
        switch (bookChoice) {
            case BOOK_LIST:
                FullList.print();
                break;
            case SEARCH:
                Search.print();
                break;
            default:
                Details.print();
                break;
        }
    }

    public void reservationMenu(ReservationMenu reservationChoice) {
        if(reservationChoice.equals(ReservationMenu.NEW_RESERVATION)) {
            AddReservation.print();
        } else{
                DeleteReservation.print();
        }
    }

    public void settingsMenu(SettingsMenu settingsChoice) {
        switch (settingsChoice) {
            case CONFIGURATIONS:
                ChangeConfiguration.print();
                break;
            case SORTING:
                SortingOptions.print();
                break;
            default:
                DataFormat.print();
                break;
        }
    }

    public static void showMenu() {
        for (MainMenu mainMenu : MainMenu.values()) {
            int mainMenuPosition = mainMenu.ordinal() + 1;
            STDOUT.info(SHOW_MENU, mainMenuPosition, mainMenu.getMenuDescription());
        }
    }

    public static void showBooksMenu() {
        STDOUT.info("W tej sekcji możesz przegladać zbiory książek. \n");
        STDOUT.info("Możesz również wyszukać daną pozycję i wyświetlić jej szczegóły. \n");
        STDOUT.info("Wybierz pozycję z menu wprowadzając jej numer. \n");
        for (BookListMenu bookListMenu : BookListMenu.values()) {
            int bookPosition = bookListMenu.ordinal() + 1;
            STDOUT.info(SHOW_MENU, bookPosition, bookListMenu.getBookDescription());
        }
        STDOUT.info(GO_BACK);
    }

    public static void showReservationMenu() {
        STDOUT.info("W tej sekcji możesz dokonać rezerwacji lub anulować rezerwację.\n");
        STDOUT.info("Wybierz czynność, którą chcesz wykonać wprowadzając jej numer. \n");
        for (ReservationMenu reservationMenu : ReservationMenu.values()) {
            int reservationPosition = reservationMenu.ordinal() + 1;
            STDOUT.info(SHOW_MENU, reservationPosition, reservationMenu.getReservationValue());
        }
        STDOUT.info(GO_BACK);

    }

    public static void showSettingsMenu() {
        STDOUT.info("W tej sekcji możesz dokonać konfiguracji sortowania i formatu wyświetlania daty. \n");
        STDOUT.info("Wybierz pozycję z menu wprowadzając jej numer. \n");
        for (SettingsMenu settingsMenu : SettingsMenu.values()) {
            int settingsPosition = settingsMenu.ordinal() + 1;
            STDOUT.info(SHOW_MENU, settingsPosition, settingsMenu.getSettingsValue());
        }
        STDOUT.info(GO_BACK);
    }
}
