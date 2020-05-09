package com.infoshareacademy.menu.item;

public enum MainMenu {
    BROWSE("PRZEGLĄDANIE ZBIORÓW"),
    FAVOURITES("ULUBIONE"),
    SORTING("SORTOWANIE"),
    EXIT("WYJŚCIE");

    String menuDescription;

    MainMenu(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

}
