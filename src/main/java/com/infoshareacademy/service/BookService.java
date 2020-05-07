package com.infoshareacademy.service;

import com.infoshareacademy.object.*;
import com.infoshareacademy.repository.BookRepository;

import java.util.Map;
import java.util.stream.Collectors;

public class BookService {
    public Map<Long, Book> findAllBooks() {
        return BookRepository.getInstance().getBooks();
    }

    public int getBooksSize() {
        return findAllBooks().size();
    }

    public String getBookDetails(Long bookChoice) {
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
        String author = books.get(bookChoice).getAuthors().stream()
                .map(Author::getName)
                .collect(Collectors.joining(", "));
        String translator = books.get(bookChoice).getTranslators().stream()
                .map(Author::getName)
                .collect(Collectors.joining(", "));
        String epoch = books.get(bookChoice).getEpochs().stream()
                .map(Epoch::getName)
                .collect(Collectors.joining(", "));
        String genre = books.get(bookChoice).getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.joining(", "));
        String kind = books.get(bookChoice).getKinds().stream()
                .map(Kind::getName)
                .collect(Collectors.joining(", "));
        String bookIsbn = isbn.getIsbn(books.get(bookChoice));
        String bookFragment = fragment.getFragment(books.get(bookChoice));
        String bookMedia = books.get(bookChoice).getMedia().stream()
                .map(Media::getName)
                .collect(Collectors.joining(", "));

        return "1. Tytuł: " + title + "\n" +
                "2. Autorzy: " + author + "\n" +
                "3. Tłumacze: " + translator + "\n" +
                "4. Epoka: " + epoch + "\n" +
                "5. Gatunek: " + genre + "\n" +
                "6. Gatunek literacki: " + kind + "\n" +
                "7. ISBN: " + bookIsbn + "\n" +
                "8. Fragment książki: " + bookFragment + "\n" +
                "9. Media: " + bookMedia + "\n\n\n";

    }

}