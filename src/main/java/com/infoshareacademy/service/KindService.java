package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Kind;

import java.util.List;
import java.util.Optional;

public class KindService {

    public static final String NONE = " - ";

    public String getKinds(Book book) {
        List<Kind> kinds = book.getKinds();
        return Optional.ofNullable(book)
                .map(Book::getAuthors)
                .filter(i -> kinds.size() > 0)
                .map(i -> kinds.get(0))
                .map(Kind::getName)
                .orElse(NONE);
    }
}
