package com.infoshareacademy.action.search;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.infoshareacademy.action.BookListService;
import com.infoshareacademy.input.UserInput;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;
import com.infoshareacademy.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Filtration {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public static void run(CriteriaChoice userCriteria) {
        BookService bookService = new BookService();
        Map<Long, Book> filteredBooks = bookService.findAllBooks();

        if(userCriteria.getActiveCriteria()[0])
            filteredBooks = filteredBooks.entrySet().stream()
                    .filter(x -> x.getValue().getTitle().toLowerCase().
                            contains(userCriteria.getTitle()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if(userCriteria.getActiveCriteria()[1])
        filteredBooks = filteredBooks.entrySet().stream()
                .filter(x -> x.getValue().getAuthors().get(0).getName().toLowerCase().
                        contains(userCriteria.getAuthor()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if(userCriteria.getActiveCriteria()[2])
            filteredBooks = filteredBooks.entrySet().stream()
                    .filter(x -> x.getValue().hasAudio() == userCriteria.getAudio())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        BookListService filteredList = new BookListService();
        filteredList.run(filteredBooks);
    }
}
