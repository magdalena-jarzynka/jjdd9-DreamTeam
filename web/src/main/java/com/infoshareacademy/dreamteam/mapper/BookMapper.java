package com.infoshareacademy.dreamteam.mapper;

import com.infoshareacademy.dreamteam.domain.entity.*;
import com.infoshareacademy.dreamteam.domain.pojo.*;
import com.infoshareacademy.dreamteam.domain.view.BookView;
import com.infoshareacademy.dreamteam.service.AuthorService;
import com.infoshareacademy.dreamteam.service.EpochService;
import com.infoshareacademy.dreamteam.service.GenreService;
import com.infoshareacademy.dreamteam.service.KindService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class BookMapper {

    @Inject
    private AuthorService authorService;
    @Inject
    private EpochService epochService;
    @Inject
    private GenreService genreService;
    @Inject
    private KindService kindService;


    public BookView mapEntityToView(Book book) {
        BookView bookView = new BookView();
        bookView.setId(book.getId());
        bookView.setTitle(book.getTitle());
        bookView.setAudio(book.getAudio());
        bookView.setFragment(book.getFragment());
        bookView.setIsbn(book.getIsbn());
        bookView.setTranslators(book.getTranslators());
        if (book.getCover() == null || book.getCover().isEmpty()) {
            bookView.setCover("/images/missing-cover.png");
        } else {
            bookView.setCover(book.getCover());
        }
        return bookView;
    }

    public Book mapPlainToEntity(BookDetailsPlain bookDetailsPlain) {
        Book book = new Book();
        book.setId(bookDetailsPlain.getId());
        book.setTitle(bookDetailsPlain.getTitle());
//        book.setAudio(!bookDetailsPlain.getAudioLength().isEmpty());
        FragmentData fragmentData = new FragmentData();
        book.setFragment(fragmentData.getFragment(bookDetailsPlain));
//        book.setIsbn(bookDetailsPlain.getIsbn());
        book.setTranslators(bookDetailsPlain.getTranslators()
                .stream()
                .map(TranslatorPlain::getName)
                .collect(Collectors.joining(", ")));
        if (bookDetailsPlain.getCover() == null || bookDetailsPlain.getCover().isEmpty()) {
            book.setCover("/images/missing-cover.png");
        } else {
            book.setCover(bookDetailsPlain.getCover());
        }
        List<Author> authors = new ArrayList<>();
        for (AuthorPlain a : bookDetailsPlain.getAuthors()) {
            Author author = authorService.findByName(a.getName());
            author.getBooks().add(book);
            authors.add(author);
        }
        book.setAuthors(authors);

        List<Epoch> epochs = new ArrayList<>();
        for (EpochPlain e : bookDetailsPlain.getEpochs()) {
            Epoch epoch = epochService.findByName(e.getName());
            epoch.getBooks().add(book);
            epochs.add(epoch);
        }
        book.setEpochs(epochs);

        List<Genre> genres = new ArrayList<>();
        for (GenrePlain g : bookDetailsPlain.getGenres()) {
            Genre genre = genreService.findByName(g.getName());
            genre.getBooks().add(book);
            genres.add(genre);
        }
        book.setGenres(genres);

        List<Kind> kinds = new ArrayList<>();
        for (KindPlain k : bookDetailsPlain.getKinds()) {
            Kind kind = kindService.findByName(k.getName());
            kind.getBooks().add(book);
            kinds.add(kind);
        }
        book.setKinds(kinds);

        return book;
    }

}
