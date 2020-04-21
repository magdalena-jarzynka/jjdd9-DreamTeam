package com.infoshareacademy.menu.item;

public enum MainMenu {
    BROWSE("PRZEGLĄDANIE ZBIORÓW"),
    BOOK_RESERVATION("REZERWACJA KSIĄŻKI"),
    SETTINGS("USTAWIENIA"),
    EXIT("WYJŚCIE");

    String menuDescription;

    MainMenu(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

}
