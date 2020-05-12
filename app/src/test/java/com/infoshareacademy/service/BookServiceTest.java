package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import org.junit.jupiter.api.Test;

import java.util.Map;

class BookServiceTest {

    @Test
    void checkIfGetsDetailsOfBookWithProperId() {
        //GIVEN
        BookService bookService = new BookService();
        Book book = new Book();
        book.setId(1L);

        String result;

        //WHEN
        result = bookService.getBookDetails(book.getId());

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isNotEmpty()
                .contains("Wiersz")
                .contains("Tadeusz Miciński")
                .doesNotContain("Jan Kochanowski");

    }

    @Test
    void checkIfContainsNullOrEmpty() {
        //GIVEN
        BookService bookService = new BookService();
        Map<Long, Book> result;

        //WHEN
        result = bookService.findAllBooks();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isNotEmpty()
                .isNotNull();

    }

}
