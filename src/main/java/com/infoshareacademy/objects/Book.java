package com.infoshareacademy.objects;

import java.util.List;

public class Book {
    private String title;
    private List<Author> authors;
    private List<Author> translators;
    private Epoch epoch;
    private List<Genre> genres;
    private Kind kind;
    private String fragment;
    private Boolean hasAudiobook;
    private String isbnPdf;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Author> getTranslators() {
        return translators;
    }

    public void setTranslators(List<Author> translators) {
        this.translators = translators;
    }

    public Epoch getEpoch() {
        return epoch;
    }

    public void setEpoch(Epoch epoch) {
        this.epoch = epoch;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public Boolean getHasAudiobook() {
        return hasAudiobook;
    }

    public void setHasAudiobook(Boolean hasAudiobook) {
        this.hasAudiobook = hasAudiobook;
    }

    public String getIsbnPdf() {
        return isbnPdf;
    }

    public void setIsbnPdf(String isbnPdf) {
        this.isbnPdf = isbnPdf;
    }
}
