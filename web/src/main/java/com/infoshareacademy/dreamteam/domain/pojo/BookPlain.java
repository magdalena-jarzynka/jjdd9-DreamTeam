package com.infoshareacademy.dreamteam.domain.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookPlain {
    private String title;
    private List<AuthorPlain> authors;
    private List<TranslatorPlain> translators;
    private List<EpochPlain> epochs;
    private List<GenrePlain> genres;
    private List<KindPlain> kinds;
    private String isbn;
    private String bookFragment;
    private List<MediaPlain> media;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorPlain> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorPlain> authors) {
        this.authors = authors;
    }

    public List<TranslatorPlain> getTranslators() {
        return translators;
    }

    public void setTranslators(List<TranslatorPlain> translators) {
        this.translators = translators;
    }

    public List<EpochPlain> getEpochs() {
        return epochs;
    }

    public void setEpochs(List<EpochPlain> epochs) {
        this.epochs = epochs;
    }

    public List<GenrePlain> getGenres() {
        return genres;
    }

    public void setGenres(List<GenrePlain> genres) {
        this.genres = genres;
    }

    public List<KindPlain> getKinds() {
        return kinds;
    }

    public void setKinds(List<KindPlain> kinds) {
        this.kinds = kinds;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookFragment() {
        return bookFragment;
    }

    public void setBookFragment(String bookFragment) {
        this.bookFragment = bookFragment;
    }

    public List<MediaPlain> getMedia() {
        return media;
    }

    public void setMedia(List<MediaPlain> media) {
        this.media = media;
    }
}
