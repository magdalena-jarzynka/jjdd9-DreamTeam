package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Genre;

import java.util.Collection;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

public class GenreService {
    Properties properties = ConstantService.readProperties("constants.properties");

    public String getGenres(Book book) {
        return Optional.ofNullable(book)
                .stream()
                .map(Book::getGenres)
                .flatMap(Collection::stream)
                .map(Genre::getName)
                .collect(Collectors.joining(", "));
    }
}
