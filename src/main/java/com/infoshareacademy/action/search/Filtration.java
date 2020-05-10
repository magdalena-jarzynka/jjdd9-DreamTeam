package com.infoshareacademy.action.search;

import com.infoshareacademy.action.BookListService;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.service.BookService;

import java.util.Map;
import java.util.stream.Collectors;

public class Filtration {

    private Filtration(){
        //Utility class
    }

    public static void run(CriteriaChoice userCriteria) {
        BookService bookService = new BookService();
        Map<Long, Book> filteredBooks = bookService.findAllBooks();

        if (userCriteria.getActiveCriteria()[0]) {
            filteredBooks = filteredBooks.entrySet().stream()
                    .filter(x -> x.getValue().getTitle().toLowerCase().
                            contains(userCriteria.getTitle()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        if (userCriteria.getActiveCriteria()[1]) {
            filteredBooks = filteredBooks.entrySet().stream()
                    .filter(x -> x.getValue().getAuthors().get(0).getName().toLowerCase().
                            contains(userCriteria.getAuthor()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        if (userCriteria.getActiveCriteria()[2]) {
            filteredBooks = filteredBooks.entrySet().stream()
                    .filter(x -> x.getValue().hasAudio() == userCriteria.getAudio())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        BookListService filteredList = new BookListService();
        filteredList.run(filteredBooks);
    }
}
