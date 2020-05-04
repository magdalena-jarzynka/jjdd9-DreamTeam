package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Epoch;

import java.util.List;
import java.util.Optional;

public class EpochService {

    public static final String NONE = " - ";

    public String getEpochs(Book book) {
        List<Epoch> epochs = book.getEpochs();
        return Optional.ofNullable(book)
                .map(Book::getAuthors)
                .filter(i -> epochs.size() > 0)
                .map(i -> epochs.get(0))
                .map(Epoch::getName)
                .orElse(NONE);
    }
}
