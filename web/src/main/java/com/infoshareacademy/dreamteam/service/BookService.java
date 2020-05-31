package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.dao.BookDao;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.view.BookView;
import com.infoshareacademy.dreamteam.mapper.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BookService {

    @EJB
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

}
