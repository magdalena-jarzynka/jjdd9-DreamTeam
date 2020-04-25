package com.infoshareacademy.comparator;

import com.infoshareacademy.object.Book;

import java.util.Comparator;

public class compareByTitle implements Comparator<Book> {
    public int compare(Book book1, Book book2) {
        return book1.getTitle().compareTo(book2.getTitle());
    }
}
