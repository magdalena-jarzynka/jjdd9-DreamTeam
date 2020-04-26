package com.infoshareacademy.comparator;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;

import java.util.Comparator;

public class compareByAuthor implements Comparator<Book> {
    public int compare(Book book1, Book book2) {
        Author author1 = book1.getAuthors().get(0);
        Author author2 = book2.getAuthors().get(0);
        return author1.getName().compareTo(author2.getName());
    }
}
