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
    private final Menu menu = new Menu();
    private static final Main main = new Main();
    private int input;

    public Main(){
        this.input = 0;
    }

    public static void main(String[] args) {
        main.run();
    }

    void run() {
        while (true) {
            input = main.openMainMenu();
            STDOUT.info("\n");
            if (input == 1) {
                main.chooseBooksMenu();
            }

            if (input == 2) {
                main.chooseReservationMenu();
            }

            if (input == 3) {
                main.chooseSettingsMenu();
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
                menu.getBooksMenu(BookListMenu.BOOK_LIST);
                break;
            case 2:
                menu.getBooksMenu(BookListMenu.SEARCH);
                break;
            case 3:
                menu.getBooksMenu(BookListMenu.BOOK_DETAILS);
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
                menu.getReservationMenu(ReservationMenu.NEW_RESERVATION);
                break;
            case 2:
                menu.getReservationMenu(ReservationMenu.CANCEL_RESERVATION);
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
                menu.getSettingsMenu(SettingsMenu.CONFIGURATIONS);
                break;
            case 2:
                menu.getSettingsMenu(SettingsMenu.SORTING);
                break;
            default:
                menu.getSettingsMenu(SettingsMenu.FORMAT);
                break;
        }
    }

    private int openMainMenu() {
        STDOUT.info("Witaj! Wybierz pozycję z Menu wpisując jej numer lub wciśnij ENTER by wyjść: \n");
        menu.showMainMenu();
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }

    private int openBooksMenu() {
        menu.showBooksMenu();
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }

    private int openReservationMenu() {
        menu.showReservationMenu();
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }

    private int openSettingsMenu() {
        menu.showSettingsMenu();
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }
}