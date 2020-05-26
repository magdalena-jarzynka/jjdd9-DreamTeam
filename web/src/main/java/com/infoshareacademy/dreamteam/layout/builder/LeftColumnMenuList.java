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

    LeftColumnMenuList(String tagDescription) {
        this.label = tagDescription;
    }

    public String getLabel() {
        return label;
    }
}
