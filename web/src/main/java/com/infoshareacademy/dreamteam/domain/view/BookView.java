package com.infoshareacademy.dreamteam.domain.view;

import java.util.ArrayList;
import java.util.List;

public class BookView {

    private Long id;
    private String title;
    private String fragment;
    private String cover;
    private String isbn;
    private boolean audio;

    private List<GenreView> genreViews = new ArrayList<>();
    private List<AuthorView> authorViews = new ArrayList<>();
    private List<TranslatorView> translatorViews = new ArrayList<>();
    private List<KindView> kindViews = new ArrayList<>();
    private List<EpochView> epochViews = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isAudio() {
        return audio;
    }

    public void setAudio(boolean audio) {
        this.audio = audio;
    }

    public List<GenreView> getGenreViews() {
        return genreViews;
    }

    public void setGenreViews(List<GenreView> genreViews) {
        this.genreViews = genreViews;
    }

    public List<AuthorView> getAuthorViews() {
        return authorViews;
    }

    public void setAuthorViews(List<AuthorView> authorViews) {
        this.authorViews = authorViews;
    }

    public List<TranslatorView> getTranslatorViews() {
        return translatorViews;
    }

    public void setTranslatorViews(List<TranslatorView> translatorViews) {
        this.translatorViews = translatorViews;
    }

    public List<EpochView> getEpochViews() {
        return epochViews;
    }

    public void setEpochViews(List<EpochView> epochViews) {
        this.epochViews = epochViews;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<KindView> getKindViews() {
        return kindViews;
    }

    public void setKindViews(List<KindView> kindViews) {
        this.kindViews = kindViews;
    }
}
