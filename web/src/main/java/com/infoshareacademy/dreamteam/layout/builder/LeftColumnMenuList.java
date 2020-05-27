package com.infoshareacademy.dreamteam.layout.builder;

public enum LeftColumnMenuList {
    MAIN_PAGE("STRONA GŁÓWNA"),
    BROWSE("KSIĘGOZBIÓR"),
    SEARCH("WYSZUKAJ"),
    GENRES("GATUNKI"),
    FAVOURITES("ULUBIONE"),
    RESERVATIONS("REZERWACJE"),
    STATS("STATYSTYKI"),
    MANAGE("ZARZĄDZANIE");

    private String label;

    LeftColumnMenuList(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
