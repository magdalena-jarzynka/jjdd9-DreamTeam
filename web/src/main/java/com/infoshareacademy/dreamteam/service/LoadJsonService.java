package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.cdi.FileUploadProcessor;
import com.infoshareacademy.dreamteam.domain.entity.*;
import com.infoshareacademy.dreamteam.domain.pojo.*;
import com.infoshareacademy.dreamteam.parser.BookParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class LoadJsonService {
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
    private TranslatorService translatorService;

    @Transactional
    public void loadFromJson(Part part) throws IOException {
        BookParser bookParser = new BookParser();
        List<BookPlain> bookList = bookParser.readBookList(fileUploadProcessor.uploadFile(part));
        List<Book> books = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Kind> kinds = new ArrayList<>();
        List<Epoch> epochs = new ArrayList<>();
        List<Translator> translators = new ArrayList<>();

        for (BookPlain bookPlain : bookList) {
            Book book = findBookEntityByTitle(bookPlain.getTitle(), books);

            if (book == null) {
                book = new Book();
                book.setTitle(bookPlain.getTitle());
                book.setIsbn(bookPlain.getIsbn());
                book.setCover(bookPlain.getCover());
                book.setAudio(!bookPlain.getAudio().isEmpty());
                FragmentData fragmentData = new FragmentData();
                book.setFragment(fragmentData.getFragment(bookPlain));
            }

            for (AuthorPlain authorPlain : bookPlain.getAuthors()) {
                Author author = findAuthorEntityByName(authorPlain.getName(), authors);
                if (author == null) {
                    author = new Author();
                    author.setName(authorPlain.getName());
                    authorService.save(author);
                    authors.add(author);
                }
                author.getBooks().add(book);
                book.getAuthors().add(author);
            }

            for (GenrePlain genrePlain : bookPlain.getGenres()) {
                Genre genre = findGenreEntityByName(genrePlain.getName(), genres);
                if (genre == null) {
                    genre = new Genre();
                    genre.setName(genrePlain.getName());
                    genreService.save(genre);
                    genres.add(genre);
                }
                genre.getBooks().add(book);
                book.getGenres().add(genre);
            }
            for (KindPlain kindPlain : bookPlain.getKinds()) {
                Kind kind = findKindEntityByName(kindPlain.getName(), kinds);
                if (kind == null) {
                    kind = new Kind();
                    kind.setName(kindPlain.getName());
                    kindService.save(kind);
                    kinds.add(kind);
                }
                kind.getBooks().add(book);
                book.getKinds().add(kind);
            }
            for (EpochPlain epochPlain : bookPlain.getEpochs()) {
                Epoch epoch = findEpochEntityByName(epochPlain.getName(), epochs);
                if (epoch == null) {
                    epoch = new Epoch();
                    epoch.setName(epochPlain.getName());
                    epochService.save(epoch);
                    epochs.add(epoch);

                }
                epoch.getBooks().add(book);
                book.getEpochs().add(epoch);
            }
            for (TranslatorPlain translatorPlain : bookPlain.getTranslators()) {
                Translator translator = findTranslatorEntityByName(translatorPlain.getName(), translators);
                if (translator == null) {
                    translator = new Translator();
                    translator.setName(translatorPlain.getName());
                    translatorService.save(translator);
                    translators.add(translator);
                }
                translator.getBooks().add(book);
                book.getTranslators().add(translator);
            }
            bookService.update(book);
        }

    }

    private Author findAuthorEntityByName(String name, List<Author> authors) {
        return authors.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst().orElse(null);
    }

    private Genre findGenreEntityByName(String name, List<Genre> genres) {
        return genres.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst().orElse(null);
    }

    private Kind findKindEntityByName(String name, List<Kind> kinds) {
        return kinds.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst().orElse(null);
    }

    private Translator findTranslatorEntityByName(String name, List<Translator> translators) {
        return translators.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst().orElse(null);
    }

    private Epoch findEpochEntityByName(String name, List<Epoch> epochs) {
        return epochs.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst().orElse(null);
    }

    private Book findBookEntityByTitle(String title, List<Book> books) {
        return books.stream()
                .filter(a -> a.getTitle().equals(title))
                .findFirst().orElse(null);
    }

}
