package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Epoch;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class EpochService {

    public String getEpochs(Book book) {
        return Optional.ofNullable(book)
                .stream()
                .map(Book::getEpochs)
                .flatMap(Collection::stream)
                .map(Epoch::getName)
                .collect(Collectors.joining(", "));
    }
}
