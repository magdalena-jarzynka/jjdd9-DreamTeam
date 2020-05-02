package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Media;

import java.util.List;
import java.util.Optional;

public class MediaService {

    public String getMedia(Book book) {
        List<Media> media = book.getMedia();
        return Optional.ofNullable(book)
                .map(Book::getAuthors)
                .filter(i -> media.size() > 0)
                .map(i -> media.get(0))
                .map(Media::getName)
                .orElse(" - ");
    }
}
