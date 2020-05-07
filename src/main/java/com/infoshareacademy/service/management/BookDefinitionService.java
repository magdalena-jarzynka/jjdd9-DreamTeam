package com.infoshareacademy.service.management;

import com.infoshareacademy.object.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;

public class BookDefinitionService {
    private final FragmentData fragmentData = new FragmentData();
    private final Scanner scanner = new Scanner(System.in);


    public void defineTitle(Book book, String title) {
        book.setTitle(title);
    }

    public void defineAuthor(Book book) {
        List<Author> authors = new ArrayList<>();
        do {
            Author author = new Author();
            STDOUT.info("Wpisz imię i nazwisko autora: ");
            author.setName(scanner.nextLine());
            authors.add(author);
            STDOUT.info("Wprowadź 0, aby zakończyć dodawanie autorów.\n");
            STDOUT.info("Naciśnij ENTER, aby dodać kolejnego autora.\n");
            String userChoice = scanner.nextLine();
            if (userChoice.contains("0")) {
                book.setAuthors(authors);
                break;
            }
        } while (true);
    }

    public void defineTranslator(Book book) {
        List<Author> translators = new ArrayList<>();
        do {
            Author translator = new Author();
            STDOUT.info("Wpisz imię i nazwisko tłumacza: ");
            translator.setName(scanner.nextLine());
            translators.add(translator);
            STDOUT.info("Wprowadź 0, aby zakończyć dodawanie autorów.\n");
            STDOUT.info("Naciśnij ENTER, aby dodać kolejnego autora.\n");
            String userChoice = scanner.nextLine();
            if (userChoice.contains("0")) {
                book.setTranslators(translators);
                break;
            }
        } while (true);
    }

    public void defineEpoch(Book book) {
        List<Epoch> epochs = new ArrayList<>();
        do {
            Epoch epoch = new Epoch();
            STDOUT.info("Wpisz nazwę epoki: ");
            epoch.setName(scanner.nextLine());
            epochs.add(epoch);
            STDOUT.info("Wprowadź 0, aby zakończyć dodawanie epok.\n");
            STDOUT.info("Naciśnij ENTER, aby kolejną epokę.\n");
            String userChoice = scanner.nextLine();
            if (userChoice.contains("0")) {
                book.setEpochs(epochs);
                break;
            }
        } while (true);
    }

    public void defineGenre(Book book) {
        List<Genre> genres = new ArrayList<>();
        do {
            Genre genre = new Genre();
            STDOUT.info("Wpisz nazwę gatunku: ");
            genre.setName(scanner.nextLine());
            genres.add(genre);
            STDOUT.info("Wprowadź 0, aby zakończyć dodawanie gatunków.\n");
            STDOUT.info("Naciśnij ENTER, aby kolejny gatunek.\n");
            String userChoice = scanner.nextLine();
            if (userChoice.contains("0")) {
                book.setGenres(genres);
                break;
            }
        } while (true);
    }

    public void defineKind(Book book) {
        List<Kind> kinds = new ArrayList<>();
        do {
            Kind kind = new Kind();
            STDOUT.info("Wpisz nazwę gatunku literackiego: ");
            kind.setName(scanner.nextLine());
            kinds.add(kind);
            STDOUT.info("Wprowadź 0, aby zakończyć dodawanie gatunków literackich.\n");
            STDOUT.info("Naciśnij ENTER, aby kolejny gatunek literacki.\n");
            String userChoice = scanner.nextLine();
            if (userChoice.contains("0")) {
                book.setKinds(kinds);
                break;
            }
        } while (true);
    }

    public void defineIsbn(Book book, String isbn) {
        book.setIsbnPdf(isbn);
    }

    public void defineFragment(Book book, String html) {
        fragmentData.setHtml(html);
        book.setBookFragment(fragmentData);
    }

    public void defineMedia(Book book) {
        List<Media> mediaList = new ArrayList<>();

        do {
            Media media = new Media();
            STDOUT.info("Wpisz tytuł autdiobooka: ");
            media.setName(scanner.nextLine());
            mediaList.add(media);
            STDOUT.info("Wprowadź 0, aby zakończyć dodawanie tytułów audiobooków.\n");
            STDOUT.info("Naciśnij ENTER, aby kolejny audiobook.\n");
            String userChoice = scanner.nextLine();
            if (userChoice.contains("0")) {
                book.setMedia(mediaList);
                break;
            }
        } while (true);
    }

}
