package com.infoshareacademy.service;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AuthorServiceTest {

    @Test
    void addTwoAutorsToListOfAuthors() {
        //GIVEN
        AuthorService authorService = new AuthorService();
        Book book = new Book();
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author();
        Author author2 = new Author();
        author1.setName("Ireneusz Malutki");
        author2.setName("Ireneusz Wielki");
        authors.add(author1);
        authors.add(author2);
        book.setAuthors(authors);
        String result;

        //WHEN
        result = authorService.getAuthors(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Ireneusz Malutki, Ireneusz Wielki")
                .isNotEqualTo("Ireneusz Malutki")
                .isNotEmpty();
    }

    @Test
    void checkIfEmptyWithNoAuthorsInAuthorsList() {
        //GIVEN
        AuthorService authorService = new AuthorService();
        Book book = new Book();
        List<Author> authors = new ArrayList<>();
        book.setAuthors(authors);
        String result;

        //WHEN
        result = authorService.getAuthors(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEmpty();

    }
}
