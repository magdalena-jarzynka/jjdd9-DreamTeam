package com.infoshareacademy.service;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class BookService {
    Random rnd = new Random();

    public Map<Long, Book> findAllBooks() {
        return BookRepository.getInstance().getBooks();
    }

    public int getBooksSize() {
        return findAllBooks().size();
    }

    public String toString(Long bookChoice) {
        Map<Long, Book> books = findAllBooks();
        AuthorService authors = new AuthorService();
        TranslatorService translators = new TranslatorService();
        EpochService epochs = new EpochService();
        GenreService genres = new GenreService();
        KindService kinds = new KindService();
        MediaService media = new MediaService();
        FragmentService fragment = new FragmentService();
        IsbnService isbn = new IsbnService();

        String title = books.get(bookChoice).getTitle();
        String author = authors.getAuthors(books.get(bookChoice));
        String translator = translators.getTranslators(books.get(bookChoice));
        String epoch = epochs.getEpochs(books.get(bookChoice));
        String genre = genres.getGenres(books.get(bookChoice));
        String kind = kinds.getKinds(books.get(bookChoice));
        String bookIsbn = isbn.getIsbn(books.get(bookChoice));
        String bookFragment = fragment.getFragment(books.get(bookChoice));
        String bookMedia = media.getMedia(books.get(bookChoice));

        return "1. Tytuł: " + title + "\n" +
                "2. Autorzy: " + author + "\n" +
               "3. Tłumacze: " + translator + "\n" +
                "4. Epoka: " + epoch + "\n" +
                "5. Gatunek: " + genre+ "\n" +
                "6. Gatunek literacki: " + kind+ "\n" +
                "7. ISBN " + bookIsbn+ "\n" +
                "8. Fragment książki: " + bookFragment+ "\n"+
                "9. Media: " + bookMedia+ "\n\n\n";

    }
}
