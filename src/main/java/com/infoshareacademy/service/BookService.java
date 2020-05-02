package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;

import java.util.Map;

public class BookService {

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

        return "1. Tytuł: " + books.get(bookChoice).getTitle() + "\n" +
                "2. Autorzy: " + authors.getAuthors(books.get(bookChoice)) + "\n" +
                "3. Tłumacze: " + translators.getTranslators(books.get(bookChoice)) + "\n" +
                "4. Epoka: " + epochs.getEpochs(books.get(bookChoice)) + "\n" +
                "5. Gatunek: " + genres.getGenres(books.get(bookChoice)) + "\n" +
                "6. Gatunek literacki: " + kinds.getKinds(books.get(bookChoice)) + "\n" +
                "7. ISBN: " + books.get(bookChoice).getIsbnPdf() + "\n" +
                "8. Fragment książki: " + books.get(bookChoice).getBookFragment() + "\n" +
                "9. Media: " + media.getMedia(books.get(bookChoice));
    }
}
