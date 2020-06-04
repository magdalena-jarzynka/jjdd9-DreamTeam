package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.dao.BookDao;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.view.BookView;
import com.infoshareacademy.dreamteam.mapper.*;
import com.infoshareacademy.dreamteam.repository.BookRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class BookService {

    private static final int BOOKS_PER_PAGE = 20;

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
        return initializeBookView(book);
    }

    private BookView initializeBookView(Book book) {
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

    public long countBooksByAudioAndGenre(String audio, String genre) {
        Boolean audioBoolean = convertAudio(audio);
        genre = convertGenre(genre);
        return bookDao.countBooksByAudioAndGenre(audioBoolean, genre);
    }

    private String convertGenre(String genre) {
        if ("blank".equals(genre)) {
            genre = null;
        }
        return genre;
    }

    private Boolean convertAudio(String audio) {
        Boolean audioBoolean;
        if (audio == null || "blank".equals(audio)) {
            audioBoolean = null;
        } else {
            audioBoolean = Boolean.valueOf(audio);
        }
        return audioBoolean;
    }

    public List<BookView> findBooks(int offset) {
        List<BookView> bookViews = new ArrayList<>();
        for (Book book : bookDao.findBooks(offset, BOOKS_PER_PAGE)) {
            bookViews.add(initializeBookView(book));
        }
        return bookViews;
    }

    public List<BookView> findBooksByAudioAndGenre(int offset, String audio, String genre) {
        List<BookView> bookViews = new ArrayList<>();
        Boolean audioBoolean = convertAudio(audio);
        genre = convertGenre(genre);

        for (Book book : bookDao.findBooksByAudioAndGenre(offset, BOOKS_PER_PAGE, audioBoolean, genre)) {
            bookViews.add(initializeBookView(book));
        }
        return bookViews;
    }
}
