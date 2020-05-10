package com.infoshareacademy.object;

import org.junit.jupiter.api.Test;

import java.util.List;

class BookTest {

    @Test
    void getSetID() {
        //GIVEN
        Book book = new Book();
        book.setId(5L);
        long result;

        //WHEN
        result = book.getId();


        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(5L)
                .isNotEqualTo(0);

    }


    @Test
    void getSetTitle() {
        //GIVEN
        Book book = new Book();
        book.setTitle("example");
        String result;

        //WHEN
        result = book.getTitle();


        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("example")
                .doesNotContainAnyWhitespaces()
                .isNotBlank()
                .isNotEqualTo("EXAMPLE");
    }

    @Test
    void getSetAuthors() {
        //GIVEN
        Book book = new Book();
        Author author1 = new Author();
        Author author2 = new Author();
        book.setAuthors(List.of(author1, author2));
        List<Author> result;

        //WHEN
        result = book.getAuthors();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(List.of(author1, author2))
                .isNotEqualTo(List.of(author1))
                .isNotEmpty();
    }

    @Test
    void getSetTranslators() {
        //GIVEN
        Book book = new Book();
        Author translator1 = new Author();
        Author translator2 = new Author();
        book.setTranslators(List.of(translator1, translator2));
        List<Author> result;

        //WHEN
        result = book.getTranslators();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(List.of(translator1, translator2))
                .isNotEqualTo(List.of(translator1))
                .isNotEmpty();
    }

    @Test
    void getSetEpochs() {
        //GIVEN
        Book book = new Book();
        Epoch epoch1 = new Epoch();
        Epoch epoch2 = new Epoch();
        book.setEpochs(List.of(epoch1, epoch2));
        List<Epoch> result;

        //WHEN
        result = book.getEpochs();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(List.of(epoch1, epoch2))
                .isNotEqualTo(List.of(epoch1))
                .isNotEmpty();
    }

    @Test
    void getSetGenres() {
        //GIVEN
        Book book = new Book();
        Genre genre1 = new Genre();
        Genre genre2 = new Genre();
        book.setGenres(List.of(genre1, genre2));
        List<Genre> result;

        //WHEN
        result = book.getGenres();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(List.of(genre1, genre2))
                .isNotEqualTo(List.of(genre1))
                .isNotEmpty();
    }

    @Test
    void getSetKinds() {
        //GIVEN
        Book book = new Book();
        Kind kind1 = new Kind();
        Kind kind2 = new Kind();
        book.setKinds(List.of(kind1, kind2));
        List<Kind> result;

        //WHEN
        result = book.getKinds();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(List.of(kind1, kind2))
                .isNotEqualTo(List.of(kind1))
                .isNotEmpty();
    }

    @Test
    void getSetIsbn() {
        //GIVEN
        Book book = new Book();
        book.setIsbnPdf("555-555");
        String result;

        //WHEN
        result = book.getIsbnPdf();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("555-555")
                .doesNotContainAnyWhitespaces()
                .isNotBlank()
                .isNotEqualTo("555555");
    }

    @Test
    void name() {
        //GIVEN


        //WHEN


        //THEN


    }

    @Test
    void getSetMedia() {
        //GIVEN
        Book book = new Book();
        Media media1 = new Media();
        Media media2 = new Media();
        book.setMedia(List.of(media1, media2));
        List<Media> result;

        //WHEN
        result = book.getMedia();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(List.of(media1, media2))
                .isNotEqualTo(List.of(media1))
                .isNotEmpty();
    }

}