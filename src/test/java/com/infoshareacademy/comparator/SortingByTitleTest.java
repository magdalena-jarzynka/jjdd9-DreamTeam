package com.infoshareacademy.comparator;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SortingByTitleTest {
    @Test
    void compareTwoBooksWithDifferentTitles() {
        //GIVEN
        SortingByTitle sortingByTitle = new SortingByTitle();
        Book book1 = new Book();
        Book book2 = new Book();
        Map.Entry<Long, Book> bookMap1 = new Map.Entry<>() {
            @Override
            public Long getKey() {
                return 1L;
            }

            @Override
            public Book getValue() {
                return book1;
            }

            @Override
            public Book setValue(Book book) {
                return book = book1;
            }
        };

        Map.Entry<Long, Book> bookMap2 = new Map.Entry<>() {
            @Override
            public Long getKey() {
                return 2L;
            }

            @Override
            public Book getValue() {
                return book2;
            }

            @Override
            public Book setValue(Book book) {
                return book = book2;
            }
        };

        String title1 = "Chapter 1";
        String title2 = "Chapter 2";
        bookMap1.getValue().setTitle(title1);
        bookMap2.getValue().setTitle(title2);
        int result;

        //WHEN
        result = sortingByTitle.compare(bookMap1, bookMap2);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(-1)
                .isNotEqualTo(0);
    }

    @Test
    void compareTwoBooksWithSameTitles() {
        //GIVEN
        SortingByTitle sortingByTitle = new SortingByTitle();
        Book book1 = new Book();
        Book book2 = new Book();
        Map.Entry<Long, Book> bookMap1 = new Map.Entry<>() {
            @Override
            public Long getKey() {
                return 1L;
            }

            @Override
            public Book getValue() {
                return book1;
            }

            @Override
            public Book setValue(Book book) {
                return book = book1;
            }
        };

        Map.Entry<Long, Book> bookMap2 = new Map.Entry<>() {
            @Override
            public Long getKey() {
                return 2L;
            }

            @Override
            public Book getValue() {
                return book2;
            }

            @Override
            public Book setValue(Book book) {
                return book = book2;
            }
        };

        String title1 = "Chapter 2";
        String title2 = "Chapter 2";
        bookMap1.getValue().setTitle(title1);
        bookMap2.getValue().setTitle(title2);
        int result;

        //WHEN
        result = sortingByTitle.compare(bookMap1, bookMap2);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(0)
                .isNotEqualTo(-1);
    }

}