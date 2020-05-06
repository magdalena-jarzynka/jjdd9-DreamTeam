package com.infoshareacademy.service.management;

import com.infoshareacademy.object.*;

import java.util.Collections;

public class BookDefinitionService {
    private final Author author = new Author();
    private final Author translator = new Author();
    private final Epoch epoch = new Epoch();
    private final Genre genre = new Genre();
    private final Kind kind = new Kind();
    private final FragmentData fragmentData = new FragmentData();
    private final Media media = new Media();


    public void defineTitle(Book book, String title) {
        book.setTitle(title);
    }

    public void defineAuthor(Book book, String name) {
        author.setName(name);
        book.setAuthors(Collections.singletonList(author));
    }

    public void defineTranslator(Book book, String name) {
        translator.setName(name);
        book.setTranslators(Collections.singletonList(translator));

    }

    public void defineEpoch(Book book, String name) {
        epoch.setName(name);
        book.setEpochs(Collections.singletonList(epoch));
    }

    public void defineGenre(Book book, String name) {
        genre.setName(name);
        book.setGenres(Collections.singletonList(genre));
    }

    public void defineKind(Book book, String name) {
        kind.setName(name);
        book.setKinds(Collections.singletonList(kind));
    }

    public void defineIsbn(Book book, String isbn) {
        book.setIsbnPdf(isbn);
    }

    public void defineFragment(Book book, String html) {
        fragmentData.setHtml(html);
        book.setBookFragment(fragmentData);
    }

    public void defineMedia(Book book, String name) {
        media.setName(name);
        book.setMedia(Collections.singletonList(media));
    }

}
