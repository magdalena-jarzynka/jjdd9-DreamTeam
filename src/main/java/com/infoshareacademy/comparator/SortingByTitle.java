package com.infoshareacademy.comparator;

import com.infoshareacademy.object.Book;

import java.util.Comparator;
import java.util.Map;

public class SortingByTitle implements Comparator<Map.Entry<Long, Book>> {

    @Override
    public int compare(Map.Entry<Long, Book> book1, Map.Entry<Long, Book> book2) {
        String title1 = book1.getValue().getTitle();
        String title2 = book2.getValue().getTitle();
        return title1.compareTo(title2);
    }
}
