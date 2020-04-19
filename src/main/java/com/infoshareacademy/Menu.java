package com.infoshareacademy;

public class Menu {
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

    static void showMenu() {
        for (MainMenu mainMenu : MainMenu.values()) {
            int mainMenuPosition = mainMenu.ordinal() + 1;
            System.out.println(mainMenuPosition + ". " + mainMenu.getMenuValue());
        }
    }

    static void showBooksMenu() {
        System.out.println("W tej sekcji możesz przegladać zbiory książek.");
        System.out.println("Możesz również wyszukać danej pozycji i wyświetlić jej szczegóły.");
        System.out.println("Wybierz pozycję z menu wprowadzając jej numer.");
        for (BookListMenu bookListMenu : BookListMenu.values()) {
            int bookPosition = bookListMenu.ordinal() + 1;
            System.out.println(bookPosition + ". " + bookListMenu.getBookValue());
        }
        System.out.println("Wybierz 0 aby wrócić do głównego Menu.");
    }

    static void showReservationMenu() {
        System.out.println("W tej sekcji możesz dokonać rezerwacji lub anulować rezerwację.");
        System.out.println("Wybierz czynność, którą chcesz wykonać wprowadzając jej numer.");
        for (ReservationMenu reservationMenu : ReservationMenu.values()) {
            int reservationPosition = reservationMenu.ordinal() + 1;
            System.out.println(reservationPosition + ". " + reservationMenu.getReservationValue());
        }
        System.out.println("Wybierz 0 aby wrócić do głównego Menu.");

    }

    static void showSettingsMenu() {
        System.out.println("W tej sekcji możesz dokonać konfiguracji sortowania i formatu wyświetlania daty.");
        System.out.println("Wybierz pozycję z menu wprowadzając jej numer.");
        for (SettingsMenu settingsMenu : SettingsMenu.values()) {
            int settingsPosition = settingsMenu.ordinal() + 1;
            System.out.println(settingsPosition + ". " + settingsMenu.getSettingsValue());
        }
        System.out.println("Wybierz 0 aby wrócić do głównego Menu.");
    }
}
