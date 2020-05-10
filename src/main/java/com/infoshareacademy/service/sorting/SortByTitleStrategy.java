package com.infoshareacademy.service.sorting;

import com.infoshareacademy.comparator.SortingByTitle;
import com.infoshareacademy.object.Book;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortByTitleStrategy implements SortStrategy {

    private SortedSet<Map.Entry<Long, Book>> books =
            new TreeSet<>(new SortingByTitle());

    @Override
    public SortedSet<Map.Entry<Long, Book>> getSortedList(Map<Long, Book> repositoryBooks) {
        books.clear();
        books.addAll(repositoryBooks.entrySet());
        return books;
    }
}
