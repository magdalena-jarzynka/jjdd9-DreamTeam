package com.infoshareacademy.dreamteam.service;


import com.infoshareacademy.dreamteam.repository.BookRepository;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.pojo.BookDetailsPlain;
import com.infoshareacademy.dreamteam.domain.pojo.BookPlain;
import com.infoshareacademy.dreamteam.domain.view.BookView;
import com.infoshareacademy.dreamteam.mapper.*;
import com.infoshareacademy.dreamteam.repository.BookRepository;
import org.apache.http.client.HttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Stateless
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class.getName());

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

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book update(Book book) {
        return bookRepository.update(book);
    }

    public Book findByTitle(String title) {
        Book book = bookRepository.findByTitle(title).orElse(null);
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

    public List<BookPlain> parseBooksFromApi(String url) throws IOException {
        Client client = ClientBuilder.newClient();
        return client.target(url)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Response.class)
                .readEntity(new GenericType<List<BookPlain>>() {
                });
    }

    public BookDetailsPlain parseBookDetailsFromApi(String url) throws HttpResponseException {
        Client client = ClientBuilder.newClient();
        BookDetailsPlain bookDetailsPlain;

        try {
            bookDetailsPlain = client
                    .register(JacksonConfig.class)
                    .target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(Response.class)
                    .readEntity(new GenericType<BookDetailsPlain>() {
                    });

        } catch (
                Exception e) {
            logger.error(e.getMessage() + " " + url, e);
            throw new HttpResponseException(422, "Bad response from book rest api");
        }
        return bookDetailsPlain;
    }
}
