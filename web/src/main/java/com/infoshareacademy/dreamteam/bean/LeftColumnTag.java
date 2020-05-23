package com.infoshareacademy.dreamteam.bean;

public enum LeftColumnTag {
    MAIN_PAGE("STRONA GŁÓWNA"),
    BROWSE("KSIĘGOZBIÓR"),
    SEARCH("WYSZUKAJ"),
    GENRES("GATUNKI"),
    FAVOURITES("ULUBIONE"),
    RESERVATIONS("REZERWACJE"),
    STATS("STATYSTYKI"),
    MANAGE("ZARZĄDZANIE");

    private String tagDescription;

    LeftColumnTag(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public String getTagDescription() {
        return tagDescription;
    }
}
