package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Media;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class MediaService {

    public String getMedia(Book book) {
        return Optional.ofNullable(book)
                .stream()
                .map(Book::getMedia)
                .flatMap(Collection::stream)
                .map(Media::getName)
                .collect(Collectors.joining(", "));
    }
}
