package com.infoshareacademy.dreamteam.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Book.findBookById",
                query = "SELECT b FROM Book b WHERE b.id=:id"
        ),
        @NamedQuery(
                name = "Book.findBookByTitle",
                query = "SELECT b FROM Book b WHERE b.title = :title"
        ),
        @NamedQuery(
                name = "Book.findAll",
                query = "SELECT b FROM Book b"
        ),
        @NamedQuery(
                name = "Book.countAll",
                query = "SELECT COUNT(b) FROM Book b"
        ),
        @NamedQuery(
                name = "Book.countWithAudioAndGenre",
                query = "SELECT COUNT(b) FROM Book b " +
                        "INNER JOIN b.genres g " +
                        "WHERE (b.audio =:audio OR :audio is null) " +
                        "AND (g.name =:genre OR :genre is null)"
        ),
        @NamedQuery(
                name = "Book.findWithAudioAndGenre",
                query = "SELECT b FROM Book b " +
                        "INNER JOIN b.genres g " +
                        "WHERE (b.audio =:audio OR :audio is null) " +
                        "AND (g.name =:genre OR :genre is null)"
        ),
})

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String isbn;

    private String fragment;

    private String cover;

    private boolean audio;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    List<Genre> genres = new ArrayList<>();

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    List<Epoch> epochs = new ArrayList<>();

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    List<Kind> kinds = new ArrayList<>();

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    List<Author> authors = new ArrayList<>();

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    List<Translator> translators = new ArrayList<>();

    @ManyToMany(mappedBy = "favourites")
    List<User> favourites = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    List<Reservation> reservations = new ArrayList<>();

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public boolean getAudio() {
        return audio;
    }

    public void setAudio(boolean audio) {
        this.audio = audio;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Epoch> getEpochs() {
        return epochs;
    }

    public void setEpochs(List<Epoch> epochs) {
        this.epochs = epochs;
    }

    public List<Kind> getKinds() {
        return kinds;
    }

    public void setKinds(List<Kind> kinds) {
        this.kinds = kinds;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Translator> getTranslators() {
        return translators;
    }

    public void setTranslators(List<Translator> translators) {
        this.translators = translators;
    }

    public List<User> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<User> favourites) {
        this.favourites = favourites;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

}
