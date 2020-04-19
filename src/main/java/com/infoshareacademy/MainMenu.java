package com.infoshareacademy;

public enum MainMenu {
    BROWSE("PRZEGLĄDANIE_ZBIORÓW"),
    BOOK_RESERVATION("REZERWACJA_KSIĄŻKI"),
    SETTINGS("USTAWIENIA"),
    EXIT("WYJŚCIE");

    String menuValue;

    MainMenu(String mv) {
        menuValue = mv;
    }

    public String getMenuValue() {
        return menuValue;
    }

}
