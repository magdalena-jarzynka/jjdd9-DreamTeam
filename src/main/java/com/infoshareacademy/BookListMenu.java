package com.infoshareacademy;

public enum BookListMenu {
    BOOK_LIST("PEŁNA_LISTA_POZYCJI"),
    SEARCH("WYSZUKIWANIE_POZYCJI"),
    BOOK_DETAILS("SZCZEGÓŁOWE_INFORMACJE");

    String bookValue;

    BookListMenu(String bv) {
        bookValue = bv;
    }

    public String getBookValue() {
        return bookValue;
    }
}
