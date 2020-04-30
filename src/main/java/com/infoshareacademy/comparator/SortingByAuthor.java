package com.infoshareacademy.comparator;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;

import java.util.Comparator;
import java.util.Map;

public class SortingByAuthor implements Comparator<Map.Entry<Long, Book>> {

//    @Override
//    public int compare(Book book1, Book book2) {
//        Author author1 = book1.getAuthors().get(0);
//        Author author2 = book2.getAuthors().get(0);
//        return author1.getName().compareTo(author2.getName());
//    }

    @Override
    public int compare(Map.Entry<Long, Book> book1, Map.Entry<Long, Book> book2) {
        Author author1 = book1.getValue().getAuthors().get(0);
        Author author2 = book2.getValue().getAuthors().get(0);
        return author1.getName().compareTo(author2.getName());
    }
}
