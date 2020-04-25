package com.infoshareacademy.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private String title;
    private List<Author> authors;
    private List<Author> translators;
    private List<Epoch> epochs;
    private List<Genre> genres;
    private List<Kind> kinds;
    @JsonProperty("isbn_pdf")
    private String isbnPdf;
    @JsonProperty("fragment_data")
    private FragmentData bookFragment;
    private List<Media> media;

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

    public List<Epoch> getEpochs() {
        return epochs;
    }

    public void setEpochs(List<Epoch> epochs) {
        this.epochs = epochs;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Kind> getKinds() {
        return kinds;
    }

    public void setKinds(List<Kind> kinds) {
        this.kinds = kinds;
    }

    public String getIsbnPdf() {
        return isbnPdf;
    }

    public void setIsbnPdf(String isbnPdf) {
        this.isbnPdf = isbnPdf;
    }

    public FragmentData getBookFragment() {
        return bookFragment;
    }

    public void setBookFragment(FragmentData bookFragment) {
        this.bookFragment = bookFragment;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public boolean hasAudio() {
        return !media.isEmpty();
    }

    public int compareByTitle(Book book1, Book book2) {
        return book1.title.compareTo(book2.title);
    }

    public int compareByAuthor(Book book1, Book book2) {
        return (book1.authors.get(0).getName()).compareTo(book2.authors.get(0).getName());
    }
}
