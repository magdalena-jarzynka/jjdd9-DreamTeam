package com.infoshareacademy.action.search;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.infoshareacademy.action.BookListService;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;

import java.util.Map;

public class Filtration {
    public static void run() {
        Map<Long, Book> filteredBooks = BookRepository.getInstance().getBooks();

        if(CriteriaChoice.getUsedCriteria()[0])
            filteredBooks = filteredBooks.entrySet().stream()
                    .filter(x -> x.getValue().getTitle().contains(Search.getTitle()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if(CriteriaChoice.getUsedCriteria()[1])
        filteredBooks = filteredBooks.entrySet().stream()
                .filter(x -> x.getValue().getAuthors().get(0).getName().contains(Search.getAuthor()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if(CriteriaChoice.getUsedCriteria()[2])
            filteredBooks = filteredBooks.entrySet().stream()
                    .filter(x -> x.getValue().hasAudio() == Search.getAudio())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


    }
}
