package com.infoshareacademy;

import com.infoshareacademy.menu.Menu;
import com.infoshareacademy.repository.BookRepository;
import com.infoshareacademy.service.sorting.SortByAuthorStrategy;
import com.infoshareacademy.service.sorting.SortByTitleStrategy;
import com.infoshareacademy.service.sorting.SortStrategy;

/**
 * DreamTeam
 */
public class Main {

    public static void main(String[] args) {
       // SortStrategy sortStrategy = new SortByAuthorStrategy();
        //        sortStrategy.getSortedList(BookRepository.getInstance().getBooks()).forEach(b -> System.out.println(b.getValue()
        //        .getTitle() + " " + b.getValue().getAuthors().get(0).getName()));
        Menu menu = new Menu();
        menu.run();
    }
}
