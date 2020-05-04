package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Media;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class MediaService {
    Properties properties = ConstantService.readProperties("constants.properties");

    public String getMedia(Book book) {
        List<Media> media = book.getMedia();
        return Optional.ofNullable(book)
                .map(Book::getAuthors)
                .filter(i -> !media.isEmpty())
                .map(i -> media.get(0))
                .map(Media::getName)
                .orElse(properties.getProperty("NONE"));
    }
}
