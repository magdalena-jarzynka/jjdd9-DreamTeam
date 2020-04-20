package com.infoshareacademy.menu.item;

public enum BookListMenu {
    BOOK_LIST("PEŁNA LISTA POZYCJI"),
    SEARCH("WYSZUKIWANIE POZYCJI"),
    BOOK_DETAILS("SZCZEGÓŁOWE INFORMACJE");

    String bookDescription;

    BookListMenu(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookDescription() {
        return bookDescription;
    }
}
