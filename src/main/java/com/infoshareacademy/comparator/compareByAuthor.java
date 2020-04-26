package com.infoshareacademy.comparator;

import com.infoshareacademy.object.Book;

import java.util.Comparator;

public class compareByAuthor implements Comparator<Book> {
    public int compare(Book book1, Book book2) {
        return (book1.getAuthors().get(0).getName()).compareTo(book2.getAuthors().get(0).getName());
    }
}
