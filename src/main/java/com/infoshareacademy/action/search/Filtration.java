package com.infoshareacademy.action.search;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.infoshareacademy.action.BookListService;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Filtration {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    public static void run() {
        Map<Long, Book> filteredBooks = BookRepository.getInstance().getBooks();

        if(CriteriaChoice.getUsedCriteria()[0])
            filteredBooks = filteredBooks.entrySet().stream()
                    .filter(x -> x.getValue().getTitle().toLowerCase().contains(CriteriaChoice.getTitle()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if(CriteriaChoice.getUsedCriteria()[1])
        filteredBooks = filteredBooks.entrySet().stream()
                .filter(x -> x.getValue().getAuthors().get(0).getName().toLowerCase().contains(CriteriaChoice.getAuthor()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if(CriteriaChoice.getUsedCriteria()[2])
            filteredBooks = filteredBooks.entrySet().stream()
                    .filter(x -> x.getValue().hasAudio() == CriteriaChoice.getAudio())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        filteredBooks.entrySet().stream()
                .forEach(x->STDOUT.info("Tytuł: {} ||| Autor: {} ||| Wersja audio: {}\n",
                        x.getValue().getTitle(),
                        x.getValue().getAuthors().get(0).getName(),
                        x.getValue().hasAudio() ? "tak" : "nie"));
    }
}
