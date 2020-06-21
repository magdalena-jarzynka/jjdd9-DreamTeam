package com.infoshareacademy.dreamteam.layout.builder;

public enum LeftColumnMenuList {
    MAIN_PAGE("STRONA GŁÓWNA"),
    BROWSE("KSIĘGOZBIÓR"),
    FAVOURITES("ULUBIONE"),
    RESERVATIONS("REZERWACJE"),
    STATS("STATYSTYKI"),
    MANAGE("ZARZĄDZANIE KSIĄŻKAMI");

    private String label;

    LeftColumnMenuList(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
