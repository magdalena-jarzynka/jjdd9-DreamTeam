package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.cdi.FileUploadProcessor;
import com.infoshareacademy.dreamteam.domain.entity.*;
import com.infoshareacademy.dreamteam.domain.pojo.*;
import com.infoshareacademy.dreamteam.parser.FileParser;
import com.infoshareacademy.dreamteam.parser.URLParser;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
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
    public void loadDatabase(List<BookPlain> bookList) {

        for (BookPlain bookPlain : bookList) {
            Book book = bookService.findByTitle(bookPlain.getTitle());

            if (book == null) {
                book = new Book();
                book.setTitle(bookPlain.getTitle());
                book.setIsbn(bookPlain.getIsbn());
                book.setCover(bookPlain.getCover());
                book.setAudio(!bookPlain.getAudio().isEmpty());
                FragmentData fragmentData = new FragmentData();
                book.setFragment(fragmentData.getFragment(bookPlain));
            } else {
                continue;
            }

            for (AuthorPlain authorPlain : bookPlain.getAuthors()) {
                Author author = authorService.findByName(authorPlain.getName());
                if (author == null) {
                    author = new Author();
                    author.setName(authorPlain.getName());
                    authorService.save(author);
                }
                author.getBooks().add(book);
                book.getAuthors().add(author);
            }

            for (GenrePlain genrePlain : bookPlain.getGenres()) {
                Genre genre = genreService.findByName(genrePlain.getName());
                if (genre == null) {
                    genre = new Genre();
                    genre.setName(genrePlain.getName());
                    genreService.save(genre);
                }
                genre.getBooks().add(book);
                book.getGenres().add(genre);
            }

            for (KindPlain kindPlain : bookPlain.getKinds()) {
                Kind kind = kindService.findByName(kindPlain.getName());
                if (kind == null) {
                    kind = new Kind();
                    kind.setName(kindPlain.getName());
                    kindService.save(kind);
                }
                kind.getBooks().add(book);
                book.getKinds().add(kind);
            }

            for (EpochPlain epochPlain : bookPlain.getEpochs()) {
                Epoch epoch = epochService.findByName(epochPlain.getName());
                if (epoch == null) {
                    epoch = new Epoch();
                    epoch.setName(epochPlain.getName());
                    epochService.save(epoch);
                }
                epoch.getBooks().add(book);
                book.getEpochs().add(epoch);
            }

            for (TranslatorPlain translatorPlain : bookPlain.getTranslators()) {
                Translator translator = translatorService.findByName(translatorPlain.getName());
                if (translator == null) {
                    translator = new Translator();
                    translator.setName(translatorPlain.getName());
                    translatorService.save(translator);
                }
                translator.getBooks().add(book);
                book.getTranslators().add(translator);
            }
            bookService.update(book);
        }
    }

    public List<BookPlain> loadFromJson(Part part) throws IOException {
        FileParser fileParser = new FileParser();
        return fileParser.readBookList(fileUploadProcessor.uploadFile(part));
    }

    public List<BookPlain> loadFromURL(URL url) throws IOException {
        URLParser urlParser = new URLParser();
        return urlParser.readBookList(url);
    }
}
