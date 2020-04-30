package com.infoshareacademy.service.sorting;

import com.infoshareacademy.object.Book;

import java.util.Map;
import java.util.SortedSet;

public interface SortStrategy {

    SortedSet<Map.Entry<Long, Book>> getSortedList(Map<Long, Book> repositoryBooks);

}
