package com.infoshareacademy.service.sorting;

import com.infoshareacademy.action.Configurations;
import com.infoshareacademy.comparator.SortingByTitle;
import com.infoshareacademy.object.Book;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortByTitleStrategy implements SortStrategy {

    @Override
    public SortedSet<Map.Entry<Long, Book>> getSortedList(Map<Long, Book> repositoryBooks) {
        SortedSet<Map.Entry<Long, Book>> books;
        switch (Configurations.getProperties().getProperty("sortingOrder")) {
            case "DESC":
                books = new TreeSet<>
                        (new SortingByTitle().reversed());
                break;
            case "ASC":
            default:
                books = new TreeSet<>
                        (new SortingByTitle());
                break;
        }
        books.addAll(repositoryBooks.entrySet());
  
        return books;
    }
}
