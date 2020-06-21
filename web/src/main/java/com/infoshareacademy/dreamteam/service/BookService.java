package com.infoshareacademy.dreamteam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.dreamteam.domain.api.BookDetailsPlain;
import com.infoshareacademy.dreamteam.domain.api.BookPlain;
import com.infoshareacademy.dreamteam.domain.api.dto.BookDto;
import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Book;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class.getName());
    private static final int BOOKS_PER_PAGE = 20;

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
    private ReservationMapper reservationMapper;

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book update(Book book) {
        return bookRepository.update(book);
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title).orElse(null);
    }

    public BookView findBookViewById(Long id) {
        Book book = bookRepository.findBookById(id)
                .orElse(new Book("Nie znaleziono książki o podanym identyfikatorze." ));
        return mapBookEntityWithRelationsToView(book);
    }

    public Book findBookById(Long id) {
        return bookRepository.findBookById(id).orElse(new Book("Nie znaleziono książki o podanym identyfikatorze." ));
    }

    private BookView mapBookEntityWithRelationsToView(Book book) {
        BookView bookView = bookMapper.mapEntityToView(book);
        book.getAuthors().forEach(author -> bookView.getAuthorViews()
                .add(authorMapper.mapEntityToView(author)));
        book.getEpochs().forEach(epoch -> bookView.getEpochViews()
                .add(epochMapper.mapEntityToView(epoch)));
        book.getGenres().forEach(genre -> bookView.getGenreViews()
                .add(genreMapper.mapEntityToView(genre)));
        book.getKinds().forEach(kind -> bookView.getKindViews()
                .add(kindMapper.mapEntityToView(kind)));
        book.getReservations().forEach(reservation -> bookView.getReservationViews()
                .add(reservationMapper.mapEntityToView(reservation)));

        return bookView;
    }

    public Book findById(Long id) {
        return bookRepository.findBookById(id)
                .orElse(null);
    }

    public List<String> getGenres() {
        return bookRepository.getGenres();
    }

    public long countBooks() {
        return bookRepository.countBooks();
    }

    public long countBooksByAudioAndGenreAndStringOfCharacters(String audio, String genre, String stringOfCharacters) {
        Boolean audioBoolean = convertAudio(audio);
        genre = convertGenre(genre);
        return bookRepository.countBooksByAudioAndGenreAndStringOfCharacters(audioBoolean, genre, stringOfCharacters);
    }

    private String convertGenre(String genre) {
        if ("blank".equals(genre)) {
            genre = null;
        }
        return genre;
    }

    private Boolean convertAudio(String audio) {
        if (audio == null || "blank".equals(audio)) {
            return null;
        } else {
            return Boolean.valueOf(audio);
        }
    }

    public List<BookView> findBooks(int offset) {
        List<BookView> bookViews = new ArrayList<>();
        for (Book book : bookRepository.findBooks(offset, BOOKS_PER_PAGE)) {
            bookViews.add(mapBookEntityWithRelationsToView(book));
        }
        return bookViews;
    }

    public List<BookView> findBooksByAudioAndGenreAndStringOfCharacters(int offset, String audio, String genre, String stringOfCharacters) {
        List<BookView> bookViews = new ArrayList<>();
        Boolean audioBoolean = convertAudio(audio);
        genre = convertGenre(genre);

        for (Book book : bookRepository.findBooksByAudioAndGenreAndStringOfCharacters(offset, BOOKS_PER_PAGE, audioBoolean, genre, stringOfCharacters)) {
            bookViews.add(mapBookEntityWithRelationsToView(book));
        }
        return bookViews;
    }

    public String getSearchListJson(String searchString) {
        List<BookView> bookViews = new ArrayList<>();

        for (Book book : bookRepository.findBooksByStringOfCharacters(searchString)) {
            bookViews.add(mapBookEntityWithRelationsToView(book));
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(bookViews);
        } catch (JsonProcessingException e) {
            logger.error("Problem with creating json from bookViews\n", e);
        }
        return null;
    }

    public List<BookPlain> parseBooksFromApi(String url) {
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
                    .target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(Response.class)
                    .readEntity(new GenericType<BookDetailsPlain>() {
                    });

        } catch (
                Exception e) {
            logger.error(e.getMessage() + " " + url, e);
            throw new HttpResponseException(422, "Bad response from book rest api" );
        }
        return bookDetailsPlain;
    }

    public List<String> getSearchList(String searchString) {
        List<String> searchList = new ArrayList<>();

        for (Book book : bookRepository.findBooksByStringOfCharacters(searchString)) {
            searchList.add(book.getTitle() + book.getAuthors().stream().map(Author::getName).collect(Collectors.joining(", " )));
        }
        return searchList;
    }

    public long saveBookDto(BookDto bookDto) {
        Book book = bookMapper.mapToEntity(bookDto);
        bookRepository.save(book);
        return book.getId();
    }

    public void deleteBook(Long bookId) {
        bookRepository.delete(bookId);
    }
}
