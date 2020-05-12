package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;

import java.util.Map;

public class BookService {
    private Map<Long, Book> books = findAllBooks();
    private FragmentService fragment = new FragmentService();
    private IsbnService isbn = new IsbnService();
    private AuthorService author = new AuthorService();
    private EpochService epoch = new EpochService();
    private GenreService genre = new GenreService();
    private KindService kind = new KindService();
    private MediaService media = new MediaService();
    private TranslatorService translator = new TranslatorService();

    public Map<Long, Book> findAllBooks() {
        return BookRepository.getInstance().getBooks();
    }

    public String getBookDetails(Long bookChoice) {
        return "1. Tytuł: " + books.get(bookChoice).getTitle() + "\n" +
                "2. Autorzy: " + author.getAuthors(books.get(bookChoice)) + "\n" +
                "3. Tłumacze: " + translator.getTranslators(books.get(bookChoice)) + "\n" +
                "4. Epoka: " + epoch.getEpochs(books.get(bookChoice)) + "\n" +
                "5. Gatunek: " + genre.getGenres(books.get(bookChoice)) + "\n" +
                "6. Gatunek literacki: " + kind.getKinds(books.get(bookChoice)) + "\n" +
                "7. ISBN: " + isbn.getIsbn(books.get(bookChoice)) + "\n" +
                "8. Fragment książki: " + fragment.getFragment(books.get(bookChoice)) + "\n" +
                "9. Media: " + media.getMedia(books.get(bookChoice)) + "\n\n\n";
    }

}