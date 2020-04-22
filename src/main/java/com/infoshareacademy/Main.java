package com.infoshareacademy;

import com.infoshareacademy.menu.Menu;
import com.infoshareacademy.menu.item.BookListMenu;
import com.infoshareacademy.menu.item.ReservationMenu;
import com.infoshareacademy.menu.item.SettingsMenu;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * DreamTeam
 */
public class Main {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String WRONG_NUMBER = "Proszę wpisać odpowiednią cyfrę.";
    private static final Scanner scanner = new Scanner(System.in);
    private static final Menu menu = new Menu();
    private static int input = 0;

    public static void main(String[] args) {
        Main.run();
    }

    static void run() {
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

    private static void chooseBooksMenu() {
        int bookChoice = openBooksMenu();
        switch (bookChoice) {
            case 1:
                menu.booksMenu(BookListMenu.BOOK_LIST);
                break;
            case 2:
                menu.booksMenu(BookListMenu.SEARCH);
                break;
            case 3:
                menu.booksMenu(BookListMenu.BOOK_DETAILS);
                break;
            default:
                input = openMainMenu();
                break;
        }
    }

    private static void chooseReservationMenu() {
        int reservationChoice = openReservationMenu();
        switch (reservationChoice) {
            case 1:
                menu.reservationMenu(ReservationMenu.NEW_RESERVATION);
                break;
            case 2:
                menu.reservationMenu(ReservationMenu.CANCEL_RESERVATION);
                break;
            default:
                input = openMainMenu();
                break;
        }
    }

    private static void chooseSettingsMenu() {
        int settingsChoice = openSettingsMenu();
        switch (settingsChoice) {
            case 1:
                menu.settingsMenu(SettingsMenu.CONFIGURATIONS);
                break;
            case 2:
                menu.settingsMenu(SettingsMenu.SORTING);
                break;
            default:
                menu.settingsMenu(SettingsMenu.FORMAT);
                break;
        }
    }

    private static int openMainMenu() {
        STDOUT.info("Witaj! Wybierz pozycję z Menu wpisując jej numer lub wciśnij ENTER by wyjść: \n");
        menu.showMenu();
        String lineInput = scanner.nextLine();
        int input = 0;
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }

    private static int openBooksMenu() {
        menu.showBooksMenu();
        String lineInput = scanner.nextLine();
        int input = 0;
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }

    private static int openReservationMenu() {
        menu.showReservationMenu();
        String lineInput = scanner.nextLine();
        int input = 0;
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }

    private static int openSettingsMenu() {
        menu.showSettingsMenu();
        String lineInput = scanner.nextLine();
        int input = 0;
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }
}