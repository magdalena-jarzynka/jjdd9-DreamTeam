package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.concurrent.Processor;
import com.infoshareacademy.dreamteam.domain.api.*;
import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Epoch;
import com.infoshareacademy.dreamteam.domain.entity.Genre;
import com.infoshareacademy.dreamteam.domain.entity.Kind;
import com.infoshareacademy.dreamteam.mapper.BookMapper;
import com.infoshareacademy.dreamteam.parser.FileParser;
import com.infoshareacademy.dreamteam.parser.FileUploadProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;


@Singleton
@Startup
public class LoadDatabaseService {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabaseService.class.getName());

    private static final int THREADS_NUMBER = 4;

    private static final String BASE_URL = "http://isa-proxy.blueazurit.com/books";
    private static final String AUTHORS_URL = "/authors/";
    private static final String BOOKS_URL = "/books/";
    private static final String GENRES_URL = "/genres/";
    private static final String EPOCHS_URL = "/epochs/";
    private static final String KINDS_URL = "/kinds/";

    @Inject
    private FileUploadProcessor fileUploadProcessor;
    @Inject
    private BookService bookService;
    @Inject
    private AuthorService authorService;
    @Inject
    private GenreService genreService;
    @Inject
    private KindService kindService;
    @Inject
    private EpochService epochService;
    @Inject
    private BookMapper bookMapper;

    @Inject
    private Executor executorService;

    public void loadAuthorsDatabase(List<AuthorPlain> authors) {
        for (AuthorPlain authorPlain : authors) {
            Author author = new Author();
            author.setName(authorPlain.getName());
            author.setReservationCount(0L);
            authorService.save(author);
        }
    }

    public void loadEpochsDatabase(List<EpochPlain> epochs) {
        for (EpochPlain epochPlain : epochs) {
            Epoch epoch = new Epoch();
            epoch.setName(epochPlain.getName());
            epochService.save(epoch);
        }
    }

    public void loadGenresDatabase(List<GenrePlain> genres) {
        for (GenrePlain genrePlain : genres) {
            Genre genre = new Genre();
            genre.setName(genrePlain.getName());
            genreService.save(genre);
        }
    }

    public void loadKindsDatabase(List<KindPlain> kinds) {
        for (KindPlain kindPlain : kinds) {
            Kind kind = new Kind();
            kind.setName(kindPlain.getName());
            kindService.save(kind);
        }
    }

    public void loadBooksDatabase(List<BookPlain> books) {

        int minItemsPerThread = books.size() / THREADS_NUMBER;
        int maxItemsPerThread = minItemsPerThread + 1;
        int threadsWithMaxItems = books.size() - THREADS_NUMBER * minItemsPerThread;
        int startIndex = 0;

        for (int i = 0; i < THREADS_NUMBER; i++) {

            int itemsCount = i < threadsWithMaxItems ? maxItemsPerThread : minItemsPerThread;
            int endIndex = startIndex + itemsCount;

            Processor r = new Processor(books.subList(startIndex, endIndex), bookMapper, bookService);
            executorService.execute(r);
            startIndex = endIndex;
        }
    }

    @PostConstruct
    public void loadBooksFromAPI() throws IOException {
        long start = System.currentTimeMillis();
        loadAuthorsDatabase(authorService.parseAuthorsFromApi(BASE_URL + AUTHORS_URL));
        loadEpochsDatabase(epochService.parseEpochsFromApi(BASE_URL + EPOCHS_URL));
        loadGenresDatabase(genreService.parseGenresFromApi(BASE_URL + GENRES_URL));
        loadKindsDatabase(kindService.parseKindsFromApi(BASE_URL + KINDS_URL));
        loadBooksDatabase(bookService.parseBooksFromApi(BASE_URL + BOOKS_URL));
        logger.info("Loading took: {}", System.currentTimeMillis() - start);
    }

    public List<BookPlain> loadFromJson(Part part) throws IOException {
        FileParser fileParser = new FileParser();
        return fileParser.readBookList(fileUploadProcessor.uploadFile(part));
    }

}
