package com.infoshareacademy.dreamteam.domain.api.dto;

import java.util.List;

public class BookDto {
    private String title;
    private List<AuthorDto> authors;
    private String translators;
    private List<EpochDto> epochs;
    private List<GenreDto> genres;
    private List<KindDto> kinds;
    private String isbn;
    private String cover;
    private String fragmentData;
    private Boolean audio;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }

    public String getTranslators() {
        return translators;
    }

    public void setTranslators(String translators) {
        this.translators = translators;
    }

    public List<EpochDto> getEpochs() {
        return epochs;
    }

    public void setEpochs(List<EpochDto> epochs) {
        this.epochs = epochs;
    }

    public List<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }

    public List<KindDto> getKinds() {
        return kinds;
    }

    public void setKinds(List<KindDto> kinds) {
        this.kinds = kinds;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getFragmentData() {
        return fragmentData;
    }

    public void setFragmentData(String fragmentData) {
        this.fragmentData = fragmentData;
    }

    public Boolean getAudio() {
        return audio;
    }

    public void setAudio(Boolean audio) {
        this.audio = audio;
    }
}
