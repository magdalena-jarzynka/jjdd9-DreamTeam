package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Kind;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class KindService {

    public String getKinds(Book book) {
        return Optional.ofNullable(book)
                .stream()
                .map(Book::getKinds)
                .flatMap(Collection::stream)
                .map(Kind::getName)
                .collect(Collectors.joining(", "));
    }
}
