package com.infoshareacademy.dreamteam.service;


import com.infoshareacademy.dreamteam.dao.BookDao;
import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Genre;
import com.infoshareacademy.dreamteam.domain.view.BookRowView;
import com.infoshareacademy.dreamteam.domain.view.BookView;
import com.infoshareacademy.dreamteam.mapper.*;
import com.infoshareacademy.dreamteam.repository.BookRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class BookService {

    @EJB
    private BookRepository bookRepository;

    @Inject
    private BookDao bookDao;

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
        return bookRepository.findByTitle(title).orElse(null);
    }

    public BookView findBookById(Long id) {
        Book book = bookDao.findBookById(id)
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
        return bookDao.getGenres();
    }

    public long countBooks() {
        return bookDao.countBooks();
    }

    public long countBooks(String audio, String genre) {
        return bookDao.countBooks(audio, genre);
    }

    public List<BookRowView> findBooks(int offset) {
        List<BookRowView> bookRowViews = new ArrayList<>();
        for (Book book : bookDao.findBooks(offset)) {
            getBookRowViews(bookRowViews, book);
        }
        return bookRowViews;
    }

    public List<BookRowView> findBooks(int offset, String audio, String genre) {
        List<BookRowView> bookRowViews = new ArrayList<>();
        for (Book book : bookDao.findBooks(offset, audio, genre)) {
            getBookRowViews(bookRowViews, book);
        }
        return bookRowViews;
    }

    private List<BookRowView> getBookRowViews(List<BookRowView> bookRowViews, Book book) {
        BookRowView bookRowView = new BookRowView();
        bookRowView.setId(book.getId());
        bookRowView.setTitle(book.getTitle());
        bookRowView.setAuthors(book.getAuthors().stream().map(Author::getName).collect(Collectors.toList()));
        bookRowView.setGenres(book.getGenres().stream().map(Genre::getName).collect(Collectors.toList()));
        bookRowView.setAudio(book.getAudio());
        bookRowViews.add(bookRowView);
        return bookRowViews;
    }
}
