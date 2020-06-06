package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.cdi.FileUploadProcessor;
import com.infoshareacademy.dreamteam.domain.entity.*;
import com.infoshareacademy.dreamteam.domain.pojo.*;
import com.infoshareacademy.dreamteam.parser.FileParser;
import org.apache.http.client.HttpResponseException;
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
    private static final String FORMAT_URL = "?format=json";


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
    private Executor executorService;

    public void loadAuthorsDatabase(List<AuthorPlain> authors) {
        for (AuthorPlain authorPlain : authors) {
            Author author = new Author();
            author.setName(authorPlain.getName());
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

            Processor r = new Processor(books.subList(startIndex, endIndex));
            executorService.execute(r);
            startIndex = endIndex;
        }
    }

// TODO wydzielic processor
    public class Processor implements Runnable {

        List<BookPlain> bookPlains;

        public Processor(List<BookPlain> subBooksPlain) {
            bookPlains = subBooksPlain;
        }

        public void run() {
            for (BookPlain bookPlain : bookPlains) {
                BookDetailsPlain bookDetailsPlain;
                try {
                    bookDetailsPlain = bookService.parseBookDetailsFromApi(bookPlain.getHref());
                } catch (HttpResponseException e) {
                    continue;
                }
                Book book = new Book();
                book.setTitle(bookDetailsPlain.getTitle());
                book.setCover(bookDetailsPlain.getCover());
                book.setIsbn(bookDetailsPlain.getIsbn());
//    TODO        book.setFragment(bookDetailsPlain.getBookFragment().getHtml());
//        TODO        book.setAudio(bookPlain.getAudio());
                // TODO relacje
                // TODO wywalić translatora, uporządkować formatowanie oraz importy
                bookService.save(book);
            }
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
