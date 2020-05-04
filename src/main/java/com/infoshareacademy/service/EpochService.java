package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Epoch;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class EpochService {
    Properties properties = ConstantService.readProperties("constants.properties");

    public String getEpochs(Book book) {
        List<Epoch> epochs = book.getEpochs();
        return Optional.ofNullable(book)
                .map(Book::getAuthors)
                .filter(i -> !epochs.isEmpty())
                .map(i -> epochs.get(0))
                .map(Epoch::getName)
                .orElse(properties.getProperty("NONE"));
    }
}
