package com.infoshareacademy;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * DreamTeam
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Menu menu = new Menu();

    public static void main(String[] args) {
        Main.run();
    }

    static void run() throws NumberFormatException {
        while (true) {
            try {
                int input = mainMenu();
                System.out.println("");
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
                    System.out.println("Program został wyłączony. Miłego dnia!");
                    System.exit(0);
                }
                else{
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Wprowadzono nieprawidłowy numer. Spróbuj ponownie.");
                    System.out.println("");
                    System.out.println("");
                }

            } catch (NumberFormatException e) {
                System.out.println("");
                System.out.println("");
                System.out.println("Proszę wybrać odpowiedni numer.");
                System.out.println("");
                System.out.println("");
            }

        }

    }


    private static int mainMenu() {
        System.out.println("Witaj! Wybierz pozycję z Menu wpisując jej numer lub wciśnij ENTER by wyjść:");
        menu.showMenu();
        int input = Integer.parseInt(scanner.nextLine());
        return input;
    }

    private static int booksMenu() {
        menu.showBooksMenu();
        int input = Integer.parseInt(scanner.nextLine());
        return input;
    }

    private static int reservationMenu() {
        menu.showReservationMenu();
        int input = Integer.parseInt(scanner.nextLine());
        return input;
    }

    private static int settingsMenu() {
        menu.showSettingsMenu();
        int input = Integer.parseInt(scanner.nextLine());
        return input;
    }
}