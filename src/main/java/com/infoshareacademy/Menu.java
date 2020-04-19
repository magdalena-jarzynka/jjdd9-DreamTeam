package com.infoshareacademy;

public class Menu {
    private BookListMenu bookChoice;
    private ReservationMenu reservationChoice;
    private SettingsMenu settingsChoice;

    public void booksMenu(BookListMenu bookChoice){
        this.bookChoice = bookChoice;
        switch (bookChoice) {
            case PEŁNA_LISTA:
                FullList.Print();
                break;
            case SZUKAJ_POZYCJI:
                Search.Print();
                break;
            case SZCZEGÓŁY_POZYCJI:
                Details.Print();
                break;
        }
    }

    public void reservationMenu(ReservationMenu reservationChoice){
        this.reservationChoice = reservationChoice;
        switch(reservationChoice){
            case DODAJ_REZERWACJĘ:
                AddReservation.Print();
                break;
            case USUŃ_REZERWACJĘ:
                DeleteReservation.Print();
                break;
        }
    }

    public void settingsMenu(SettingsMenu settingsChoice){
        this.settingsChoice = settingsChoice;
        switch (settingsChoice){
            case ZMIEŃ_KONFIGURACJĘ:
                ChangeConfiguration.Print();
                break;
            case SOROTWANIE_POZYCJI:
                SortingOptions.Print();
                break;
            case ZMIEŃ_FORMAT:
                DataFormat.Print();
                break;
        }
    }

    static int showMenu() {
        Choice[] choices = Choice.values();
        for (int i = 0; i < choices.length; i++) {
            int j = i + 1;
            System.out.println(j + ". " + choices[i]);
        }
        int menuLength = choices.length;
        return menuLength;
    }

    static int showBooksMenu(){
        System.out.println("W tej sekcji możesz przegladać zbiory książek.");
        System.out.println("Możesz również wyszukać danej pozycji i wyświetlić jej szczegóły.");
        System.out.println("Wybierz pozycję z menu wprowadzając jej numer.");
        BookListMenu[] books = BookListMenu.values();
        for (int i = 0; i < books.length; i++) {
            int j = i + 1;
            System.out.println(j + ". " + books[i]);
        }
        System.out.println("Wybierz 0 aby wrócić do głównego Menu.");
        int booksLength = books.length;
        return booksLength;
    }

    static int showReservationMenu(){
        System.out.println("W tej sekcji możesz dokonać rezerwacji lub anulować rezerwację.");
        System.out.println("Wybierz czynność, którą chcesz wykonać wprowadzając jej numer.");
        ReservationMenu[] reservationMenu = ReservationMenu.values();
        for (int i = 0; i < reservationMenu.length; i++) {
            int j = i + 1;
            System.out.println(j + ". " + reservationMenu[i]);
        }
        System.out.println("Wybierz 0 aby wrócić do głównego Menu.");
        int reservationMenuLength = reservationMenu.length;
        return reservationMenuLength;
    }

    static int showSettingsMenu(){
        System.out.println("W tej sekcji możesz dokonać konfiguracji sortowania i formatu wyświetlania daty.");
        System.out.println("Wybierz pozycję z menu wprowadzając jej numer.");
        SettingsMenu[] settingsMenu = SettingsMenu.values();
        for (int i = 0; i < settingsMenu.length; i++) {
            int j = i + 1;
            System.out.println(j + ". " + settingsMenu[i]);
        }
        System.out.println("Wybierz 0 aby wrócić do głównego Menu.");
        int settingsMenuLength = settingsMenu.length;
        return settingsMenuLength;
    }
}
