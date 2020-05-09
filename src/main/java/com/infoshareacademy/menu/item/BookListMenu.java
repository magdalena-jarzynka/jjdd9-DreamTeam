package com.infoshareacademy.menu.item;

public enum BookListMenu {
    BOOK_LIST("PEŁNA LISTA POZYCJI"),
    SEARCH("WYSZUKIWANIE POZYCJI"),
    BOOKS_MANAGEMENT("ZARZĄDZANIE KSIĄŻKAMI");

    String bookDescription;

    BookListMenu(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookDescription() {
        return bookDescription;
    }
}
