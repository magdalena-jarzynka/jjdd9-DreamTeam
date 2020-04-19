package com.infoshareacademy;
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

    static void run() throws IllegalArgumentException  {
        while (true) {
            try {
                int input = mainMenu();
                System.out.println("");
                if (input == 1) {
                    int bookChoice = booksMenu();
                    switch (bookChoice) {
                        case 1:
                            menu.booksMenu(BookListMenu.PEŁNA_LISTA);
                            break;
                        case 2:
                            menu.booksMenu(BookListMenu.SZUKAJ_POZYCJI);
                            break;
                        case 3:
                            menu.booksMenu(BookListMenu.SZCZEGÓŁY_POZYCJI);
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
                            menu.reservationMenu(ReservationMenu.DODAJ_REZERWACJĘ);
                            break;
                        case 2:
                            menu.reservationMenu(ReservationMenu.USUŃ_REZERWACJĘ);
                        case 0:
                            input = mainMenu();
                            break;
                    }
                }

                if (input == 3) {
                    int settingsChoice = settingsMenu();
                    switch (settingsChoice) {
                        case 1:
                            menu.settingsMenu(SettingsMenu.ZMIEŃ_KONFIGURACJĘ);
                            break;
                        case 2:
                            menu.settingsMenu(SettingsMenu.SOROTWANIE_POZYCJI);
                            break;
                        case 3:
                            menu.settingsMenu(SettingsMenu.ZMIEŃ_FORMAT);
                            break;
                    }
                }

                if (input == 4) {
                    System.out.println("The program has terminated. Thank you for using the program. Have a nice day!");
                    System.exit(0);
                }

            } catch (Exception e) {
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