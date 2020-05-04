package com.infoshareacademy.service.sorting;

import com.infoshareacademy.action.Configurations;
import com.infoshareacademy.comparator.SortingByAuthor;
import com.infoshareacademy.object.Book;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortByAuthorStrategy implements SortStrategy {

    @Override
    public SortedSet<Map.Entry<Long, Book>> getSortedList(Map<Long, Book> repositoryBooks) {
        SortedSet<Map.Entry<Long, Book>> books;
        switch (Configurations.getProperties().getProperty("sortingOrder")) {
            case "DESC":
                books = new TreeSet<>
                        (new SortingByAuthor().reversed());
                break;
            case "ASC":
            default:
                books = new TreeSet<>
                        (new SortingByAuthor());
                break;
        }
        books.addAll(repositoryBooks.entrySet());

        return books;
    }
}
