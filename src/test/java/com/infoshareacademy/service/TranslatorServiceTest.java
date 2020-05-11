package com.infoshareacademy.service;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TranslatorServiceTest {

    @Test
    void addTwoAutorsToListOfAuthors() {
        //GIVEN
        TranslatorService translatorService = new TranslatorService();
        Book book = new Book();
        List<Author> translators = new ArrayList<>();
        Author translator1 = new Author();
        Author translator2 = new Author();
        translator1.setName("Ireneusz Malutki");
        translator2.setName("Ireneusz Wielki");
        translators.add(translator1);
        translators.add(translator2);
        book.setTranslators(translators);
        String result;

        //WHEN
        result = translatorService.getTranslators(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Ireneusz Malutki, Ireneusz Wielki")
                .isNotEqualTo("Ireneusz Malutki")
                .isNotEmpty();
    }

    @Test
    void checkIfEmptyWithNoAuthorsInAuthorsList() {
        //GIVEN
        TranslatorService translatorService = new TranslatorService();
        Book book = new Book();
        List<Author> translators = new ArrayList<>();
        book.setTranslators(translators);
        String result;

        //WHEN
        result = translatorService.getTranslators(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEmpty();

    }

}
