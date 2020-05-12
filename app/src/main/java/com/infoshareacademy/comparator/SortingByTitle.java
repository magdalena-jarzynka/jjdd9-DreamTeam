package com.infoshareacademy.comparator;

import com.infoshareacademy.object.Book;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

public class SortingByTitle implements Comparator<Map.Entry<Long, Book>> {

    @Override
    public int compare(Map.Entry<Long, Book> book1, Map.Entry<Long, Book> book2) {
        Collator c = Collator.getInstance(new Locale("pl", "PL"));
        String title1 = book1.getValue().getTitle().toLowerCase();
        String title2 = book2.getValue().getTitle().toLowerCase();
        return c.compare(title1, title2);
    }
}
