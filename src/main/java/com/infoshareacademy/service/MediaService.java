package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Media;

import java.util.List;

public class MediaService {

    public String getMedia (Book book) {
        List<Media> media = book.getMedia();
        return String.join(", ", media.get(0).getName());
    }
}
