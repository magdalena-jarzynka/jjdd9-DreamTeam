package com.infoshareacademy;

import com.infoshareacademy.menu.item.BookListMenu;
import com.infoshareacademy.menu.Menu;
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
    private static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    private static final Scanner scanner = new Scanner(System.in);
    private static final Menu menu = new Menu();

    public static void main(String[] args) {
        Main.run();
    }

    static void run() {
        while (true) {

            int input = mainMenu();
            STDOUT.info("\n");
            if (input == 1) {
                int bookChoice = booksMenu();
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
                    case 0:
                        input = mainMenu();
                        break;
                }
            }
            if (input == 2) {
                int reservationChoice = reservationMenu();
                switch (reservationChoice) {
                    case 1:
                        menu.reservationMenu(ReservationMenu.NEW_RESERVATION);
                        break;
                    case 2:
                        menu.reservationMenu(ReservationMenu.CANCEL_RESERVATION);
                    case 0:
                        input = mainMenu();
                        break;
                }
            }

            if (input == 3) {
                int settingsChoice = settingsMenu();
                switch (settingsChoice) {
                    case 1:
                        menu.settingsMenu(SettingsMenu.CONFIGURATIONS);
                        break;
                    case 2:
                        menu.settingsMenu(SettingsMenu.SORTING);
                        break;
                    case 3:
                        menu.settingsMenu(SettingsMenu.FORMAT);
                        break;
                }
            }

            if (input == 4) {
                STDOUT.info("Program został wyłączony. Miłego dnia!");
                break;
            }

        }

    }

    private static int mainMenu() {
        STDOUT.info("Witaj! Wybierz pozycję z Menu wpisując jej numer lub wciśnij ENTER by wyjść: \n");
        menu.showMenu();
        String lineInput = scanner.nextLine();
        int input = 0;
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info("Proszę wpisać odpowiednią cyfrę.");
        }
        return input;
    }

    private static int booksMenu() {
        menu.showBooksMenu();
        String lineInput = scanner.nextLine();
        int input = 0;
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info("Proszę wpisać odpowiednią cyfrę.");
        }
        return input;
    }

    private static int reservationMenu() {
        menu.showReservationMenu();
        String lineInput = scanner.nextLine();
        int input = 0;
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info("Proszę wpisać odpowiednią cyfrę.");
        }
        return input;
    }

    private static int settingsMenu() {
        menu.showSettingsMenu();
        String lineInput = scanner.nextLine();
        int input = 0;
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info("Proszę wpisać odpowiednią cyfrę.");
        }
        return input;
    }
}