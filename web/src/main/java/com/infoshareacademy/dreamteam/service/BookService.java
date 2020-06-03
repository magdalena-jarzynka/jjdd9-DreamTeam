package com.infoshareacademy.dreamteam.service;


import com.infoshareacademy.dreamteam.repository.BookRepository;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.view.BookView;
import com.infoshareacademy.dreamteam.mapper.*;
import com.infoshareacademy.dreamteam.repository.BookRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class BookService {

    @EJB
    private BookRepository bookRepository;

    @Inject
    private BookMapper bookMapper;

    @Inject
    private AuthorMapper authorMapper;

    @Inject
    private EpochMapper epochMapper;

    @Inject
    private GenreMapper genreMapper;

    @Inject
    private KindMapper kindMapper;

    @Inject
    private TranslatorMapper translatorMapper;

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book update(Book book) {
        return bookRepository.update(book);
    }

    public Book findByTitle(String title) {
        Book book = bookRepository.findBookByTitle(title).orElse(null);
        return book;
    }

    public BookView findBookById(Long id) {
        Book book = bookRepository.findBookById(id)
                .orElse(new Book("Nie znaleziono książki o podanym identyfikatorze."));
        BookView bookView = bookMapper.mapEntityToView(book);
        book.getAuthors().forEach(author -> bookView.getAuthorViews()
                .add(authorMapper.mapEntityToView(author)));
        book.getEpochs().forEach(epoch -> bookView.getEpochViews()
                .add(epochMapper.mapEntityToView(epoch)));
        book.getGenres().forEach(genre -> bookView.getGenreViews()
                .add(genreMapper.mapEntityToView(genre)));
        book.getKinds().forEach(kind -> bookView.getKindViews()
                .add(kindMapper.mapEntityToView(kind)));
        book.getTranslators().forEach(translator -> bookView.getTranslatorViews()
                .add(translatorMapper.mapEntityToView(translator)));

        return bookView;
    }

    public List<String> getGenres() {
        return bookRepository.getGenres();
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public int countBooks() {
        return bookRepository.countBooks();
    }

    public int countBooks(String audio, String genre) {
        return bookRepository.countBooks(audio, genre);
    }

    public List<Book> findBooks(int offset, int limit) {

        return bookRepository.findBooks(offset, limit);
    }

    public List<Book> findBooks(int offset, int limit, String audio, String genre) {

        return bookRepository.findBooks(offset, limit, audio, genre);
    }
}
